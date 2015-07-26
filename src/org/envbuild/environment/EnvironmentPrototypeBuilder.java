package org.envbuild.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Stack;

/**
 * @author kovlyashenko
 */
@Component
public class EnvironmentPrototypeBuilder {
    private Chain rootChain;
    private Chain currentChain;
    private ChainSection currentChainSection;
    private Stack<Chain> chainsStack = new Stack<Chain>();
    private Stack<ChainSection> sectionsStack = new Stack<ChainSection>();
    @Autowired
    private DbEnvironmentBuilder environmentBuilder;

    public EnvironmentPrototypeBuilder newBuild() {
        rootChain = new Chain();
        currentChain = rootChain;
        currentChainSection = new ChainSection();
        rootChain.getSections().add(currentChainSection);
        chainsStack.clear();
        return this;
    }

    public EnvironmentPrototypeBuilder startChain() {
        Chain newChain = new Chain();
        ChainSection section = new ChainSection();
        section.setSubChain(newChain);
        currentChain.getSections().add(section);
        chainsStack.push(currentChain);
        currentChain = newChain;
        sectionsStack.push(section);
        currentChainSection = new ChainSection();
        currentChain.getSections().add(currentChainSection);
        return this;
    }

    public EnvironmentPrototypeBuilder createObject(Class<?> objectClass) {
        addAction(new PrototypeAction(PrototypeActionType.CREATE_OBJECT, objectClass), currentChainSection);
        return this;
    }

    public EnvironmentPrototypeBuilder asParent() {
        addAction(new PrototypeAction(PrototypeActionType.ADD_LAST_PARENT), currentChainSection);
        return this;
    }

    public EnvironmentPrototypeBuilder asParent(Class<?> objectClass) {
        addAction(new PrototypeAction(PrototypeActionType.ADD_LAST_PARENT, objectClass), currentChainSection);
        return this;
    }

    public EnvironmentPrototypeBuilder addParent(Object object) {
        return addParent(object, object.getClass());
    }

    public EnvironmentPrototypeBuilder addParent(Object object, Class<?> objectClass) {
        addAction(new PrototypeAction(PrototypeActionType.ADD_PARENT, object, objectClass), currentChainSection);
        return this;
    }
    
    public EnvironmentPrototypeBuilder clearParents() {
        addAction(new PrototypeAction(PrototypeActionType.CLEAR_PARENTS), currentChainSection);
        return this;
    }

    private void addAction(PrototypeAction action, ChainSection section) {
        if(section.getSubChain() == null) {
            section.getPreActions().add(action);
        } else {
            section.getPostActions().add(action);
        }
    }

    public EnvironmentPrototypeBuilder repeat(int nTimes) {
        currentChain.setRepeatCount(nTimes);

        if(!chainsStack.empty()) {
            currentChain = chainsStack.pop();
            currentChainSection = sectionsStack.pop();
        }
        return this;
    }

    public EnvironmentPrototypeBuilder build() {
        environmentBuilder.newBuild();
        buildChain(rootChain, new ParentsContext());
        return this;
    }

    private void buildChain(Chain chain, ParentsContext context) {
        for(int i = 0; i < chain.getRepeatCount(); i++) {
            setupParents(context.getContext());
            context.startNewContext();
            for(ChainSection section : chain.getSections()) {
                for (PrototypeAction action : section.getPreActions()) {
                    processAction(action, context);
                }
                if(section.getSubChain() != null) {
                    context.startNewContext();
                    buildChain(section.getSubChain(), context);
                    context.restoreContext();
                    setupParents(context.getContext());
                }
                for (PrototypeAction action : section.getPostActions()) {
                    processAction(action, context);
                }
            }
            context.restoreContext();
        }
    }

    private void setupParents(List<PrototypeAction> parents) {
        environmentBuilder.clearParents();
        if(parents != null) {
            for(PrototypeAction parent : parents) {
                if(parent.getObjectClass() == null) {
                    environmentBuilder.setParent(parent.getObject());
                } else {
                    environmentBuilder.setParent(parent.getObject(), parent.getObjectClass());
                }
            }
        }
    }

    private void processAction(PrototypeAction action, ParentsContext context) {
        switch (action.getActionType()) {
            case CREATE_OBJECT:
                environmentBuilder.createObject(action.getObjectClass()).alias("last");
                break;
            case ADD_LAST_PARENT:
                environmentBuilder.asParent(action.getObjectClass());
                action.setObject(environmentBuilder.getEnvironment().getByAlias("last"));
                context.getContext().add(action);
                break;
            case ADD_PARENT:
                environmentBuilder.setParent(action.getObject(), action.getObjectClass());
                context.getContext().add(action);
                break;
            case CLEAR_PARENTS:
                environmentBuilder.clearParents();
                context.getContext().clear();
                break;
        }
    }

    public DbEnvironment getEnvironment() {
        return environmentBuilder.getEnvironment();
    }
}
