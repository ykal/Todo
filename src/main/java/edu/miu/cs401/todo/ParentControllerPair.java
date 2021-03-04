package edu.miu.cs401.todo;

import javafx.scene.Parent;

public class ParentControllerPair<T extends Parent, S> {
    private T parent;
    private S controller;

    public ParentControllerPair(T parent, S controller) {
        this.parent = parent;
        this.controller = controller;
    }

    public T getParent() {
        return parent;
    }

    public void setParent(T parent) {
        this.parent = parent;
    }

    public S getController() {
        return controller;
    }

    public void setController(S controller) {
        this.controller = controller;
    }
}
