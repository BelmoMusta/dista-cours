package com.dista.cours.validation.visitor.statements;


import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.util.Optional;
import java.util.function.Consumer;

public class ReturnFalseBlock extends Statement {
    final BlockStmt block = new BlockStmt();

    public ReturnFalseBlock() {
        block.addStatement(new ReturnFalseStatement());
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return block.accept(v, arg);
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
        block.accept(v, arg);
    }

    public Optional<BlockStmt> toBlockStmt() {
        return Optional.of(block);
    }
    public boolean isBlockStmt() {
        return true;
    }
    public BlockStmt asBlockStmt() {
        return block;
    }
    public void ifBlockStmt(Consumer<BlockStmt> action) {
        action.accept(block);
    }
}


