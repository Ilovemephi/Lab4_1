
package mephi.b22901.ae.lab4;

import java.util.List;

public interface DAO<T> {
    void create(T item);                      
    T getById(int id);                        
    List<T> getAll();                         
    void update(T item);                      
    void delete(int id);                 
}
