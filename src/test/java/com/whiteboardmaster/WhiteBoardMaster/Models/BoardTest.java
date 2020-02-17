package com.whiteboardmaster.WhiteBoardMaster.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;
    @BeforeEach
    public void setBoard(){
        board = new Board("ProblemDomain", "Algorithm", "PseudoCode", "BigOTimeNotation", "BigOSpace", "Verification", "Code", "EdgeCase", "I/O", "Visual", "Title");
    }

    @Test
    void getBigOTimeNotation() {
        assertEquals("ProblemDomain", board.getProblemDomain());
    }

    @Test
    void getVisual() {
        assertEquals("Visual", board.getVisual());
    }
}