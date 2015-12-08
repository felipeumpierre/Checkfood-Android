package com.checkfood.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Request implements Serializable {

    private int id;
    private Board board;
    private ArrayList<RequestProduct> listRequestProduct;


    public Request() {
    }

    public Request(int id, Board board, ArrayList<RequestProduct> listRequestProduct) {
        this.id = id;
        this.board = board;
        this.listRequestProduct = listRequestProduct;
    }

    public Request(int id, ArrayList<RequestProduct> listRequestProduct) {
        this.id = id;
        this.listRequestProduct = listRequestProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<RequestProduct> getListRequestProduct() {
        return listRequestProduct;
    }

    public void setListRequestProduct(ArrayList<RequestProduct> listRequestProduct) {
        this.listRequestProduct = listRequestProduct;
    }
}


