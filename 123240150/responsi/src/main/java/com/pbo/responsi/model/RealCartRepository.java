/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pbo.responsi.model;

import com.pbo.responsi.config.DatabaseConnection;
import com.pbo.responsi.dto.CartItemDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lab Informatika
 */
public class RealCartRepository implements CartRepository{
    private final DatabaseConnection conn;
    public RealCartRepository(){
        this.conn = DatabaseConnection.getInstance();
    }
    
    @Override
    public List<CartItemDTO> findAll() {
        List<CartItemDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cart";
            Connection conn = this.conn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {                
                CartItemDTO cart = new CartItemDTO(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("qty")
                );
                result.add(cart);
            }
        } catch (Exception e) {
            System.out.println("Error : "+ e.getMessage());
        }
        return result;
    }

    @Override
    public void save(CartItemDTO item) {
        try {
            String sql = "INSERT INTO cart (name, price, qty) VALUES (?, ?, ?)";
            Connection conn = this.conn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setInt(3, item.getQuantity());
            stmt.executeUpdate();
            System.out.println("Berhasil Dimasukkan");
        } catch (Exception e) {
            System.out.println("Gagal : " + e.getMessage());
        }
    }

    @Override
    public void updateQuantity(String name, int newQty) {
        try {
            String sql = "UPDATE cart SET name=?, qty=?";
            Connection conn = this.conn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setDouble(2, newQty);
            stmt.executeUpdate();
            System.out.println("Berhasil Diupdate");
        } catch (Exception e) {
            System.out.println("Gagal : " + e.getMessage());
        }
    }

    @Override
    public void delete(String name) {
        try {
            String sql = "DELETE FROM cart where name =?";
            Connection conn = this.conn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.executeUpdate();
            System.out.println("Berhasil Dihapus");
        } catch (Exception e) {
            System.out.println("Gagal : " + e.getMessage());
        }
    }
    
}
