package strathmore.com.sqlitelab;

/**
 * Created by Briege on 11/1/2017.
 */

public class Department {

    int _id;
    String _name;
    String _manager;


    public Department(){

    }

    public Department(int id,String name,String manager){
        this._id = id;
        this._name = name;
        this._manager = manager;
    }

    public Department(String name,String manager){
        this._name = name;
        this._manager = manager;
    }

    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }

    public String getName(){
        return this._name;
    }

    public void setName(String name){
        this._name = name;
    }

    public String getManager(){
        return this._manager;
    }

    public void setManager(String manager){
        this._manager = manager;
    }
}
