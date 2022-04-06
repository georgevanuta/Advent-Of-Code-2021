package com.company.folding;

import com.company.paper.Paper;

public class FoldingContext {
    private FoldingStrategy foldingStrategy;

    public void setFoldingStrategy(FoldingStrategy foldingStrategy) {
        this.foldingStrategy = foldingStrategy;
    }

    public void executeFoldingStrategy(Paper paper, Integer byWhichValue) {
        foldingStrategy.fold(paper, byWhichValue);
    }
}
