package com.example.contactproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product);

    @Query("SELECT * FROM contacts WHERE contactName LIKE '%' || :name || '%'")
    List<Product> findProduct(String name);

    @Query("DELETE FROM contacts WHERE contactName = :name")
    void deleteProduct(String name);

    @Query("SELECT * FROM contacts")
    LiveData<List<Product>> getAllProducts();
}
