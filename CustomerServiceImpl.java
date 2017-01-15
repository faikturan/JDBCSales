package com.faikturan.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.faikturan.common.ConnectionDB;
import com.faikturan.model.Customer;
import com.faikturan.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private static final Logger log = LogManager.getLogger(CustomerServiceImpl.class);

	Connection cn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

	@Override
	public Customer findCustomerById(int id) {
		log.info("#findCustomerById");

		String sql = "SELECT c.id, " + "c.companyName, " + "c.contactName, " + "c.address, " + "c.mailContact, "
				+ "c.phoneNumber, " + "c.createdDate," + "c.updatedDate, " + "c.deletedDate, " + "c.deleted "
				+ "FROM db_sales.customer c" + "WHERE c.id = ?;";
		try {
			cn = new ConnectionDB().getConnection();
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();

			Customer customer = null;
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setCompanyName(rs.getString("companyName"));
				customer.setContactName(rs.getString("contactName"));
				customer.setAddress(rs.getString("address"));
				customer.setMailContact(rs.getString("mailContact"));
				customer.setPhoneNumber(rs.getString("phoneNumber"));
				customer.setCreatedDate(rs.getString("createdDate"));
				customer.setUpdatedDate(rs.getString("updatedDate"));
				customer.setDeletedDate(rs.getString("deletedDate"));
				customer.setDeleted(rs.getString("deleted"));
			}

			log.info(customer);
			return customer;
		} catch (Exception exception) {
			log.error(exception);
		} finally {
			try {
				if (cn != null)
					cn.close();
				if (pstm != null)
					pstm.close();
				if (rs != null)
					rs.close();
			} catch (Exception exception2) {
				log.error(exception2);
			}
		}
		return null;
	}

	@Override
	public List<Customer> getCustomerList() {
		log.info("#getCustomerList");

		String sql = "SELECT c.id, " + "c.companyName, " + "c.contactName, " + "c.address, " + "c.mailContact, "
				+ "c.phoneNumber, " + "c.createdDate," + "c.updatedDate, " + "c.deletedDate, " + "c.deleted "
				+ "FROM db_sales.customer c;";
		try {
			cn = new ConnectionDB().getConnection();
			pstm = cn.prepareStatement(sql);
			rs = pstm.executeQuery();

			List<Customer> customerList = new ArrayList<Customer>();
			Customer customer = null;
			if (rs.next()) {
				customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setCompanyName(rs.getString("companyName"));
				customer.setContactName(rs.getString("contactName"));
				customer.setAddress(rs.getString("address"));
				customer.setMailContact(rs.getString("mailContact"));
				customer.setPhoneNumber(rs.getString("phoneNumber"));
				customer.setCreatedDate(rs.getString("createdDate"));
				customer.setUpdatedDate(rs.getString("updatedDate"));
				customer.setDeletedDate(rs.getString("deletedDate"));
				customer.setDeleted(rs.getString("deleted"));
				customerList.add(customer);
			}
			
			int count = customerList.size();
			log.info("Records selected: "+count) ;
			return customerList;
		} catch (Exception exception) {
			log.error(exception);
		} finally {
			try {
				if (cn != null)
					cn.close();
				if (pstm != null)
					pstm.close();
				if (rs != null)
					rs.close();
			} catch (Exception exception2) {
				log.error(exception2);
			}
		}
		return null;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		log.info("#updateCustomer");
		
		boolean flag = false;
		String sql = "UPDATE db_sales.customer c"
				+ "SET c.companyName = ?, "
				+ "c.contactName = ?, "
				+ "c.address = ?, "
				+ "c.mailContact = ?, "
				+ "c.phoneNumber = ?, "
				+ "c.createdDate = ?,"
				+ "c.updatedDate = NOW(), "
				+ "c.deletedDate = ?, "
				+ "c.deleted= ? "
				+ "WHERE c.id = ?;";
		try {
			cn = new ConnectionDB().getConnection();
			pstm = cn.prepareStatement(sql);
			
			pstm.setString(1, customer.getCompanyName());
			pstm.setString(2, customer.getContactName());
			pstm.setString(3, customer.getAddress());
			pstm.setString(4, customer.getMailContact());
			pstm.setString(5, customer.getPhoneNumber());
			pstm.setString(6, customer.getCreatedDate());
			pstm.setString(7, customer.getDeletedDate());
			pstm.setString(8, customer.getDeleted());
			pstm.setInt(9, customer.getId());
			
			int count = pstm.executeUpdate();
			flag = count > 0 ? true : false;
			log.info("Records updated: " +count);
			
		} catch (Exception exception) {
			log.error(exception);
		}finally {
			try {
				if(cn != null)
					cn.close();
				if(pstm != null)
					pstm.close();
				if(rs != null)
					rs.close();
			} catch (Exception exception2) {
				log.error(exception2);
			}
		}
		return flag;
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

}
