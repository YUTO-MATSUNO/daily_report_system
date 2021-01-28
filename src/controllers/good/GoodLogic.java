package controllers.good;

import models.Good;

public class GoodLogic {
    public void goodPlus(Good g) {

        GoodDAO dao = new GoodDAO();
        dao.insert(g);
    }
}