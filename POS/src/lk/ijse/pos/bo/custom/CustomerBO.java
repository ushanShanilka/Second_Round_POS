package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;

import java.util.ArrayList;

public interface CustomerBO extends SuperBO{

    public boolean addCustomer( CustomerDTO customer) throws Exception ;

    public boolean deleteCustomer(String id) throws Exception ;

    public CustomerDTO searchCustomer(String id) throws Exception ;

    public boolean updateCustomer(CustomerDTO customer) throws Exception ;

    public ArrayList<CustomerDTO> getAllCustomer() throws Exception ;
}
