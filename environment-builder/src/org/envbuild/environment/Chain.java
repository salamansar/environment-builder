package org.envbuild.environment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kovlyashenko
 */
public class Chain {
    private List<ChainSection> sections = new ArrayList<ChainSection>();
    private int repeatCount = 1;

    public List<ChainSection> getSections() {
        return sections;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }
}
