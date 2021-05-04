package model.dao;

import java.sql.*;
import java.util.*;

import model.*;
import model.bean.*;

public class FileSystemModel extends Connector{

	public  ArrayList<Element> getElements(int parent_id, String orderby, String ascdesc){
		ArrayList<Element> elements = new ArrayList<Element>();

		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM elements WHERE parent_id="+parent_id+ " AND deleted=0 ORDER BY type desc, "+orderby+" "+ascdesc;
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				elements.add(new Element(
						rs.getInt("id"),
						rs.getInt("parent_id"),
						rs.getString("name"),
						rs.getString("type"),
						rs.getString("extension"),
						rs.getString("content"),
						rs.getString("created_at"),
						rs.getString("updated_at")
						));
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return elements;
	}
	
	public  ArrayList<Element> getElements(int parent_id){
		return getElements(parent_id,"name","asc");
	}
	
	public  ArrayList<Element> getElements(int parent_id, String orderby){
		return getElements(parent_id,orderby,"asc");
	}

	
	public Element getElement(int id) {
		try {
			Statement st = conn.createStatement();
			String sql = "SELECT * FROM elements WHERE id="+id+" AND deleted=0";
			ResultSet rs = st.executeQuery(sql);
			
			if(rs.next()) {
				return new Element(
						rs.getInt("id"),
						rs.getInt("parent_id"),
						rs.getString("name"),
						rs.getString("type"),
						rs.getString("extension"),
						rs.getString("content"),
						rs.getString("created_at"),
						rs.getString("updated_at")
						);
			}
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}

	public void insertElement(int parent_id, String name, String extension, String type) {
		try {
			Statement st = conn.createStatement();
			String sql = "INSERT INTO elements (parent_id, name, extension, type) VALUES("+parent_id+",'"+name+"','"+extension+"','"+type+"')";
			st.executeUpdate(sql);
			
			
		} catch (SQLException e) { e.printStackTrace(); }
		
	}

	public void deleteElement(int id) {
		// TODO Auto-generated method stub
		try {
			
			Statement st1 = conn.createStatement();
			String sql1 = "SELECT * FROM elements WHERE parent_id="+id+" AND deleted=0";
			ResultSet rs = st1.executeQuery(sql1);
			
			if(!rs.next()) {
				Statement st2 = conn.createStatement();
				String sql2 = "UPDATE elements SET deleted=1 WHERE id="+id;
				st2.executeUpdate(sql2);
			}
			
			
		} catch (SQLException e) { e.printStackTrace(); }
			
	}
}
