package Estructures.trees;

import java.time.temporal.ValueRange;

public class RB_Tree<Key extends  Comparable<Key>, V> {

    RB_node<Key,V> root;

    //Metodos principales

    public void add(V value,Key k){
        add(value , k ,root);
    }

    private void add(V value, Key k, RB_node<Key,V> ref) {
        if(root == null){
            root = new RB_node<Key,V>(k,value,false,null);
            root.setLeft(new RB_node<>(null, null, false, ref));
            root.setRight(new RB_node<>(null, null, false, ref));
        }else {



            int a = k.compareTo(ref.getKey());

            if (k.compareTo(ref.getKey()) >= 0) {
                if (ref.right.getKey() != null) {
                    add(value, k, ref.getRight());
                } else {
                    ref.setRight(new RB_node<>(k, value, true, ref));
                    ref.getRight().setRight(new RB_node<>(null, null, false, ref));
                    ref.getRight().setLeft(new RB_node<>(null, null, false, ref));
                    Fix_Up(ref.getRight());
                }
            } else {
                if (ref.left.getKey() != null) {
                    add(value, k, ref.getLeft());
                } else {
                    ref.setLeft(new RB_node<>(k, value, true, ref));
                    ref.getLeft().setRight(new RB_node<>(null, null, false, ref));
                    ref.getLeft().setLeft(new RB_node<>(null, null, false, ref));
                    Fix_Up(ref.getLeft());
                }
            }
        }
    }

    private void Fix_Up(RB_node<Key,V> child) {

        while(isRed(child.getParent())){

            if(child.getParent().getParent().getLeft() == child.getParent()){ //Left case
                RB_node uncle = child.getParent().getParent().getRight();
                if(isRed(uncle)){                 //Caso Tio Rojo
                    child.getParent().setColorRed(false);
                    uncle.setColorRed(false);
                    child.getParent().getParent().setColorRed(true);
                    child = child.getParent().getParent();

                }else{
                    if(child == child.getParent().getRight()) {        // Caso Triangulo
                        child = child.getParent();
                        rotateLeft(child);
                    }

                    //Caso Linea
                    child.getParent().setColorRed(false);
                    child.getParent().getParent().setColorRed(true);
                    rotateRight(child.getParent().getParent());

                }

            }else{                          //Right cases
                RB_node uncle = child.getParent().getParent().getLeft();
                if(isRed(uncle)){                 //Caso Tio Rojo
                    child.getParent().setColorRed(false);
                    if(uncle != null){
                        uncle.setColorRed(false);
                    }
                    child.getParent().getParent().setColorRed(true);
                    child = child.getParent().getParent();

                }else{

                    if(child == child.getParent().getLeft()) {        // Caso Triangulo
                        child = child.getParent();
                        rotateRight(child);
                    }
                    //Caso Linea
                    child.getParent().setColorRed(false);
                    child.getParent().getParent().setColorRed(true);
                    rotateLeft(child.getParent().getParent());
                }
            }
        }
        root.setColorRed(false);
    }


    public void delete(Key k) {
        RB_node<Key,V> ref = search(k);
        if(ref == root){
            root = new RB_node<>(null, null, false, null);
        }else if(ref != null) {

            RB_node<Key, V> y = ref;
            RB_node<Key, V> x = null;
            boolean isRed = ref.isColorRed();



            if (ref.getRight().getKey() == null)  {
                x = ref.getLeft();
                Transplant(ref, ref.getLeft());

            } else if (ref.getLeft().getKey() == null) {
                x = ref.getRight();
                Transplant(ref, ref.getRight());

            } else {
                y = ref.sucessor();
                isRed = y.isColorRed();
                x = y.getRight();

                if (y.getParent() == ref) {
                    x.setParent(y);

                } else {
                    Transplant(y, y.getRight());
                    y.setRight(ref.getRight());
                    y.getRight().setParent(y);
                }

                Transplant(ref, y);

                y.setLeft(ref.getLeft());
                y.getLeft().setParent(y);
                y.setColorRed(ref.isColorRed());
            }
            if (isRed == false) {
                delete_Fixup(root, x);
            }
        }
    }

    private void delete_Fixup(RB_node<Key, V> root, RB_node<Key, V> x) {
        while(x != root && !isRed(x)){

            if(x == x.getParent().getLeft()){
                RB_node<Key, V> w = x.getParent().getRight();

                if (isRed(w)){
                    w.setColorRed(false);
                    x.getParent().setColorRed(true);
                    rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }


                if(w == null){
                    System.out.println("Que mierda");
                }

                if( !isRed(w.getLeft())  &&  !isRed(w.getRight()) ){
                    w.setColorRed(true);
                    x = x.getParent();
                }else{

                    if(!isRed(w.getRight())) {
                        w.getLeft().setColorRed(false);
                        w.setColorRed(true);
                        rotateRight(w);
                        w = x.getParent().getRight();
                    }
                    w.setColorRed(isRed(x.getParent()));
                    x.getParent().setColorRed(false);

                    if(w.getRight() != null){
                        w.getRight().setColorRed(false);
                    }

                    rotateLeft(x.getParent());
                    x = root;

                }
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
                }else{

                    if(!isRed(w.getLeft())) {
                        if (w.getRight() != null) {
                            w.getRight().setColorRed(false);
                        }
                        w.setColorRed(true);
                        rotateLeft(w);
                        w = x.getParent().getLeft();
                    }

                    w.setColorRed(isRed(x.getParent()));
                    x.getParent().setColorRed(false);
                    if(w.getLeft() != null) {
                        w.getLeft().setColorRed(false);
                    }
                    rotateRight(x.getParent());
                    x = root;
                }


            }

            x.setColorRed(false);
        }
    }

    void rotateLeft(RB_node<Key, V> father){
        RB_node<Key, V> y = father.getRight();
        father.setRight(y.getLeft());

        if(y.getLeft().getKey() != null){
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

    void rotateRight(RB_node<Key, V> father){
        RB_node<Key, V> y = father.getLeft();
        father.setLeft(y.getRight());

        if(y.getRight().getKey() != null){
            y.getRight().setParent(father);
        }

        y.setParent(father.getParent());

        if(father.getParent() == null){
            root = y;
        }else if(father ==  father.getParent().getRight()){
            father.getParent().setRight(y);
        }else{
            father.getParent().setLeft(y);
        }

        y.setRight(father);
        father.setParent(y);

    }

    private void Transplant(RB_node<Key,V> ref, RB_node<Key,V> soon) {
        if(ref.getParent() == null){
            root = soon;
        }else if(ref.getParent().getLeft() == ref){
            ref.getParent().setLeft(soon);
        }else{
            ref.getParent().setRight(soon);
        }

        soon.setParent(ref.getParent());


    }


    public RB_node<Key,V> search(Key key){
        return search(key, root);
    }

    private RB_node<Key,V> search(Key key, RB_node<Key,V> ref) {
        if(ref == null || ref.getKey() == null){
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
