package org.envbuild.environment;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @author kovlyashenko
 */
public class ParentsContext {
    private List<PrototypeAction> currentParents;
    private Stack<List<PrototypeAction>> parentsStack = new Stack<List<PrototypeAction>>();

    public void startNewContext() {
        if(currentParents != null) {
            parentsStack.push(currentParents);
            currentParents = new LinkedList<PrototypeAction>(currentParents);
        } else {
            currentParents = new LinkedList<PrototypeAction>();
        }

    }

    public List<PrototypeAction> restoreContext() {
        if(!parentsStack.isEmpty()) {
            currentParents = parentsStack.pop();
        } else {
            currentParents = null;
        }

        return currentParents;
    }

    public List<PrototypeAction> getContext() {
        return currentParents;
    }
}
