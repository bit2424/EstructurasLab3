package Estructures.trees;

public class RB_Tree<Key extends  Comparable<Key>, V> {

    RB_node<Key,V> root;

    //Metodos principales

    public void add(V value,Key k){
        add(value , k ,root);
    }

    private void add(V value, Key k, RB_node<Key,V> ref) {
        if(root == null){
            root = new RB_node<Key,V>(k,value,false,null);
        }else {

            boolean right = false;

            int a = k.compareTo(ref.getKey());

            if (k.compareTo(ref.getKey()) >= 0) {
                if (ref.right != null) {
                    add(value, k, ref.getRight());
                } else {
                    ref.setRight(new RB_node<>(k, value, true, ref));
                    Fix_Up(ref.getRight(), ref);
                }
            } else {
                if (ref.left != null) {
                    add(value, k, ref.getLeft());
                } else {
                    ref.setLeft(new RB_node<>(k, value, true, ref));
                    Fix_Up(ref.getLeft(), ref);
                }
            }
        }
    }


    public void delete(Key k){
        delete( k ,root);
    }

    private void delete(Key k, RB_node<Key,V> root) {
        RB_node<Key,V> ref = search(k);
        if(ref == root){
            root = null;
        }else if(ref != null) {

            RB_node<Key, V> y = ref;
            RB_node<Key, V> x;
            boolean isRed = ref.isColorRed();

            if (ref.getRight() == null) {
                x = ref.getLeft();
                Transplant(ref, ref.getLeft(), root);
                if (isRed == false) {
                    delete_Fixup(root, x , ref);
                }
            } else if (ref.getLeft() == null) {
                x = ref.getRight();
                Transplant(ref, ref.getRight(), root);
                if (isRed == false) {
                    delete_Fixup(root, x , ref);
                }
            } else {
                y = ref.sucessor();
                isRed = y.isColorRed();
                x = y.getRight();

                if (y.getParent() == ref) {
                    if(x != null) {
                        x.setParent(y);
                    }
                } else {
                    Transplant(y, y.getRight(), root);
                    y.setRight(ref.getRight());
                    y.getRight().setParent(y);
                }

                Transplant(ref, y, root);

                y.setLeft(ref.getLeft());
                y.getLeft().setParent(y);
                y.setColorRed(ref.isColorRed());

                if (isRed == false) {
                    delete_Fixup(root, x , y);
                }
            }
        }
    }




    public RB_node<Key,V> search(Key key){
        return search(key, root);
    }

    private RB_node<Key,V> search(Key key, RB_node<Key,V> ref) {
        if(ref == null){
            return null;
        }
        if(key.equals(ref.getKey())){
            return ref;
        }else if(key.compareTo(ref.getKey()) >= 0){
            return search(key,ref.getRight());
        }else{
            return search(key,ref.getLeft());
        }

    }


    //Metodos auxiliares


    private void Fix_Up(RB_node<Key,V> child, RB_node<Key,V> parent) {
        RB_node<Key,V> uncle;
        RB_node<Key,V> granPa = parent.getParent();
        while(granPa != null && parent.isColorRed()){
            if(granPa.getLeft() == parent){ //Left case
                uncle = granPa.getRight();
                if(isRed(uncle)){                 //Caso Tio Rojo
                    parent.setColorRed(false);
                    if(uncle != null){
                        uncle.setColorRed(false);
                    }
                    granPa.setColorRed(true);
                    child = granPa;

                }else if(child == parent.right){        // Caso Triangulo
                    child = parent;
                    rotateLeft(child);

                }                                  //Caso Linea
                parent.setColorRed(false);
                granPa.setColorRed(true);
                rotateRight(granPa);

            }else{                          //Right cases
                uncle = granPa.getLeft();
                if(isRed(uncle)){                 //Caso Tio Rojo
                    parent.setColorRed(false);
                    if(uncle != null){
                        uncle.setColorRed(false);
                    }
                    granPa.setColorRed(true);
                    child = granPa;

                }else if(child == parent.left){        // Caso Triangulo
                    child = parent;
                    rotateRight(child);

                }                                 //Caso Linea
                parent.setColorRed(false);
                granPa.setColorRed(true);
                rotateLeft(granPa);
            }
        }
    }

    void rotateLeft(RB_node<Key, V> father){
        RB_node<Key, V> y = father.getRight();
        father.setRight(y.getLeft());
        if(y.getLeft() != null){
            y.getLeft().setParent(father);
        }

        y.setParent(father.getParent());

        if(father.getParent() == null){
            root = y;
        }else if(father ==  father.getParent().getLeft()){
            father.getParent().setLeft(y);
        }else{
            father.getParent().setRight(y);
        }

        y.setLeft(father);
        father.setParent(y);
    }

    void rotateRight(RB_node<Key, V> child){
        RB_node<Key, V> y = child.getLeft();
        child.setLeft(y.getRight());
        if(y.getRight() != null){
            y.getRight().setParent(child);
        }

        y.setParent(child.getParent());

        if(child.getParent() == null){
            root = y;
        }else if(child ==  child.getParent().getRight()){
            child.getParent().setRight(y);
        }else{
            child.getParent().setLeft(y);
        }

        y.setRight(child);
        child.setParent(y);

    }

    private void Transplant(RB_node<Key,V> ref, RB_node<Key,V> soon, RB_node<Key,V> root) {
            if(ref.getParent() == null){
                root = soon;
            }else if(ref.getParent().getLeft() == ref){
                ref.getParent().setLeft(soon);
            }else{
                ref.getParent().setRight(soon);
            }

            if(soon != null) {
                soon.setParent(ref.getParent());
            }
    }

    private void delete_Fixup(RB_node<Key, V> root, RB_node<Key, V> x, RB_node<Key, V> father) {
        while(x != root || !isRed(x)){



            if(x == father.getLeft()){
                RB_node<Key, V> w = father.getRight();

                if (isRed(w)){
                    w.setColorRed(false);
                    father.setColorRed(true);
                    rotateLeft(father);
                    w = father.getRight();
                }


                if( !isRed(w.getLeft())  &&  !isRed(w.getRight()) ){
                    w.setColorRed(true);
                    x = x.getParent();
                }else if(!isRed(w.getRight())){
                    if (w.getLeft() != null) {
                        w.getLeft().setColorRed(false);
                    }
                    w.setColorRed(true);
                    rotateRight(w);
                    w = x.getParent().getRight();
                }
                w.setColorRed(x.getParent().isColorRed());
                x.getParent().setColorRed(false);

                if(w.getRight() != null){
                    w.getRight().setColorRed(false);
                }

                rotateLeft(x.getParent());
                x = root;

            }else{
                RB_node<Key, V> w = x.getParent().getLeft();

                if ( isRed(w)){
                    w.setColorRed(false);
                    x.getParent().setColorRed(true);
                    rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }

                if(  !isRed(w.getRight()) && !isRed(w.getLeft())){
                    w.setColorRed(true);
                    x = x.getParent();
                }else if(!isRed(w.getLeft())){
                    if(w.getRight() != null) {
                        w.getRight().setColorRed(false);
                    }
                    w.setColorRed(true);
                    rotateLeft(w);
                    w = x.getParent().getLeft();
                }
                w.setColorRed(x.getParent().isColorRed());
                x.getParent().setColorRed(false);
                if(w.getLeft() != null) {
                    w.getLeft().setColorRed(false);
                }
                rotateRight(x.getParent());
                x = root;

            }

            x.setColorRed(false);
        }
    }


    public boolean isRed(RB_node<Key,V> ref){
        if (ref == null){
            return false;
        }else{
            if (ref.isColorRed() == true){
                return true;
            }else{
                return false;
            }
        }
    }
}
