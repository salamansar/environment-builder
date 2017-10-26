package org.envbuild.environment;

import java.util.LinkedList;
import java.util.List;

/**
 * @author kovlyashenko
 */
public class ChainSection {
    private List<PrototypeAction> preActions = new LinkedList<PrototypeAction> ();
    private Chain subChain;
    private List<PrototypeAction> postActions = new LinkedList<PrototypeAction>();

    public Chain getSubChain() {
        return subChain;
    }

    public void setSubChain(Chain subChain) {
        this.subChain = subChain;
    }

    public List<PrototypeAction> getPreActions() {
        return preActions;
    }

    public List<PrototypeAction> getPostActions() {
        return postActions;
    }
}
