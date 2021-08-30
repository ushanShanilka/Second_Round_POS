package lk.ijse.pos.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;

import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.pos.dao.custom.impl.ItemDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDAOImpl;
import lk.ijse.pos.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.model.Customer;
import lk.ijse.pos.model.Item;
import lk.ijse.pos.model.OrderDetails;
import lk.ijse.pos.model.Orders;
import lk.ijse.pos.view.tblmodel.OrderDetailTM;


import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OrderFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbCustomerID;
    @FXML
    private JFXComboBox<String> cmbItemCode;


    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtQtyOnHand;
    @FXML
    private JFXTextField txtUnitPrice;
    @FXML
    private JFXTextField txtQty;
    @FXML
    private TableView<OrderDetailTM> tblOrderDetails;

    private ObservableList<OrderDetailTM> olOrderDetails;

    private boolean update = false;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private Label lblTotal;
    @FXML
    private JFXTextField txtOrderID;
    @FXML
    private JFXDatePicker txtOrderDate;

    private Connection connection;

    /*Property  Injection*/
    private CustomerDAO customerDAO = new CustomerDAOImpl ( );

    private ItemDAO itemDAO = new ItemDAOImpl ( );

    private OrderDAO orderDAO = new OrderDAOImpl ( );

    private OrderDetailsDAO orderDetailsDAO =  new OrderDetailsDAOImpl ( );


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
             connection = DBConnection.getInstance().getConnection();

            // Create a day cell factory
            Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            // Must call super
                            super.updateItem(item, empty);
                            LocalDate today = LocalDate.now();
                            setDisable(empty || item.compareTo(today) < 0);
                        }
                    };
                }
            };

            txtOrderDate.setDayCellFactory(dayCellFactory);
            loadAllData();
        } catch (SQLException ex) {
            Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace();
        }


        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String customerID = observable.getValue();
                if (customerID == null) {
                    txtCustomerName.setText("");
                    return;
                }

                try {
//                    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
//                    pstm.setObject(1, customerID);
//                    ResultSet rst = pstm.executeQuery();

                    Customer customer = customerDAO.search ( customerID );

                    if (customer!=null) {
                        txtCustomerName.setText(customer.getName ());
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    e.printStackTrace ( );
                }

            }
        });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                String itemCode = observable.getValue();

                if (itemCode == null) {
                    txtDescription.setText("");
                    txtQtyOnHand.setText("");
                    txtUnitPrice.setText("");
                    txtQty.setText("");
                    return;
                }

                try {
//                    PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code = ?");
//                    pstm.setObject(1, itemCode);
//
//                    ResultSet rst = pstm.executeQuery();

                    Item item = itemDAO.search ( itemCode );

                    if (item!=null) {
                        String description = item.getDescription ();
                        BigDecimal unitPrice = item.getUnitPrice ();
                        int qtyOnHand = item.getQtyOnHand ();

                        txtDescription.setText(description);
                        txtUnitPrice.setText(unitPrice + "");
                        txtQtyOnHand.setText(qtyOnHand + "");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception e) {
                    e.printStackTrace ( );
                }

            }
        });

        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));

        olOrderDetails = FXCollections.observableArrayList();
        tblOrderDetails.setItems(olOrderDetails);

        tblOrderDetails.getItems().addListener(new ListChangeListener<OrderDetailTM>() {
            @Override
            public void onChanged(Change<? extends OrderDetailTM> c) {

                double total = 0.0;

                for (OrderDetailTM orderDetail : olOrderDetails) {
                    total += orderDetail.getTotal();
                }

                lblTotal.setText("Total : " + total);

            }
        });

        tblOrderDetails.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<OrderDetailTM>() {
            @Override
            public void changed(ObservableValue<? extends OrderDetailTM> observable, OrderDetailTM oldValue, OrderDetailTM newValue) {

                OrderDetailTM currentRow = observable.getValue();

                if (currentRow == null) {
                    cmbItemCode.getSelectionModel().clearSelection();
                    update = false;
                    btnRemove.setDisable(true);
                    return;
                }

                update = true;
                String itemCode = currentRow.getItemCode();
                btnRemove.setDisable(false);

                cmbItemCode.getSelectionModel().select(itemCode);
                txtQty.setText(currentRow.getQty() + "");

            }
        });

        btnRemove.setDisable(true);

    }

    private void loadAllData() throws Exception {

        ArrayList<Customer> allCustomer = customerDAO.getAll ( );

        for (Customer customer:allCustomer) {
            cmbCustomerID.getItems ().add ( customer.getcID () );
        }

        ArrayList<Item> all = itemDAO.getAll ( );
        for (Item item:all) {
            cmbItemCode.getItems ().add ( item.getCode () );
        }

    }

    @FXML
    private void navigateToMain(MouseEvent event) throws IOException {
        Label lblMainNav = (Label) event.getSource();
        Stage primaryStage = (Stage) lblMainNav.getScene().getWindow();

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/pos/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
    }

    @FXML
    private void btnSaveOnAction(ActionEvent event) {

        String itemCode = cmbItemCode.getSelectionModel().getSelectedItem();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        if (!update) {
            for (OrderDetailTM orderDetail : olOrderDetails) {
                if (orderDetail.getItemCode().equals(itemCode)) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "Please update the item instead of adding", ButtonType.OK);
                    error.setHeaderText("Duplicate Entry Found");
                    error.setTitle("Duplicate Error");
                    error.show();
                    return;
                }
            }
        }

        OrderDetailTM orderDetail = new OrderDetailTM(
                itemCode,
                txtDescription.getText(),
                qty,
                unitPrice,
                qty * unitPrice);


        if (!update) {
            olOrderDetails.add(orderDetail);
            tblOrderDetails.setItems(olOrderDetails);
        } else {
            OrderDetailTM selectedRow = tblOrderDetails.getSelectionModel().getSelectedItem();
            int index = olOrderDetails.indexOf(selectedRow);
            olOrderDetails.set(index, orderDetail);
        }

        cmbItemCode.getSelectionModel().clearSelection();
        cmbItemCode.requestFocus();

    }

    @FXML
    private void btnRemoveOnAction(ActionEvent event) {
        OrderDetailTM selectedRow = tblOrderDetails.getSelectionModel().getSelectedItem();
        olOrderDetails.remove(selectedRow);

    }

    @FXML
    private void btnPlaceOrderOnAction(ActionEvent event) {
        try {
            connection.setAutoCommit(false);

            Orders orders = new Orders ( txtOrderID.getText(),parseDate(txtOrderDate.getEditor().getText()),cmbCustomerID.getSelectionModel().getSelectedItem());
            boolean b1 = orderDAO.add ( orders );
            System.out.println (b1 );

            if (!b1) {
                connection.rollback();
                return;
            }

            for (OrderDetailTM orderDetail: olOrderDetails) {
                OrderDetails orderDetails = new OrderDetails ( txtOrderID.getText ( ) , orderDetail.getItemCode () ,orderDetail.getQty (),new BigDecimal ( orderDetail.getUnitPrice () ) );
                boolean b = orderDetailsDAO.add ( orderDetails );


                if (!b) {
                    connection.rollback();
                    return;
                }
                int qtyOnHand = 0;

                Item item = itemDAO.search ( orderDetails.getItemCode ( ) );


                if (item!=null) {
                    qtyOnHand = item.getQtyOnHand ();
                }

                boolean b2 = itemDAO.updateItemQtyOnHand ( orderDetails.getItemCode ( ) , orderDetails.getQty ( ) );
                System.out.println (b2 );

                if (!b2) {
                    connection.rollback();
                    return;
                }

            }

            connection.commit();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order Placed", ButtonType.OK);
            alert.show();

        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            e.printStackTrace ( );
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private Date parseDate ( String text ) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        try {
            return sdf.parse(text);
        } catch (ParseException ex) {

            Logger.getLogger(OrderFormController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
