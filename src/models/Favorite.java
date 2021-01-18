package models;

import java.io.Serializable;

public class Favorite implements Serializable {

        private int favoriteCount = 0;

        public void setFavoriteCount(int favoriteCount) {
            this.favoriteCount = favoriteCount;
        }

        public int getFavoriteCount() {
            return favoriteCount;
        }

}
