package mainApp.com.github.johnsonclayton.sheetmusicapp;



class Rectangle {
        int x, y,width, height; //Where x1, y1 are the top-left coords and x2, y2 are bottom-right
        boolean filled;
        Rectangle(int _x, int _y, int _width, int _height) {
            x = _x;
            y = _y;
            width = _width;
            height = _height;
            filled = false;
        }
        
        public boolean containsClick(int clicked_x, int clicked_y) {
            if((x <= clicked_x) && (y <= clicked_y) && (x + width >= clicked_x) && (y + height >= clicked_y)) {
                System.out.println("clicked inside");
                return true;
            }
            else return false;
        }
    }