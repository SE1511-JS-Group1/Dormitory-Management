/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lenovo_thinkpad
 */
public class Management {

    private Dom dom;
    private DomManager domManager;

    public Management() {
    }

    public Management(Dom dom, DomManager domManager) {
        this.dom = dom;
        this.domManager = domManager;
    }

    public Dom getDom() {
        return dom;
    }

    public void setDom(Dom dom) {
        this.dom = dom;
    }

    public DomManager getDomManager() {
        return domManager;
    }

    public void setDomManager(DomManager domManager) {
        this.domManager = domManager;
    }

}
