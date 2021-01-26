package controller;

import model.Bill;
import model.Cake;
import model.Category;
import model.Customer;
import service.BillService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@WebServlet(name = "BillServlet", urlPatterns = "/billServlet")
public class BillServlet extends HttpServlet {
    private BillService billService = new BillService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                addBill(request, response);
                break;
            case "edit":
                updateBill(request, response);
                break;
            case "delete":
                deleteBill(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action==null){
            action="";
        }
        switch (action){
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            default:
                listBill(request, response);
        }
    }

    //Delete
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Bill bill = billService.selectBill(id);
        if(bill!=null){
            request.setAttribute("bill", bill);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/bill/delete.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteBill(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Bill bill = billService.selectBill(id);
        RequestDispatcher dispatcher;
        if(bill != null){
            try {
                billService.deleteBill(id);
                dispatcher = request.getRequestDispatcher("admin/bill/delete.jsp");
                request.setAttribute("message", "Delete thanh cong");
                try {
                    dispatcher.forward(request,response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }



    // Update
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Bill bill = billService.selectBill(id);
        if(bill!=null){
            request.setAttribute("bill", bill);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/bill/edit.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateBill(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Timestamp billDate = Timestamp.valueOf(request.getParameter("billDate"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        Customer customer = billService.findCustomerById(customerId);
        Bill bill = new Bill(id, billDate, customer);
        try {
            billService.updateBill(bill);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/bill/edit.jsp");
            request.setAttribute("message", "Update thanh cong");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //    Create
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/bill/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addBill (HttpServletRequest request, HttpServletResponse response){
        Timestamp billDate = Timestamp.valueOf(request.getParameter("billDate"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        Customer customer = billService.findCustomerById(customerId);
        Bill bill = new Bill(null, billDate, customer);
        try {
            billService.insertBill(bill);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/bill/create.jsp");
            request.setAttribute("message", "Them thanh cong");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    // Display list - read
    private void listBill(HttpServletRequest request, HttpServletResponse response) {
        List<Bill> billList = billService.selectAllBills();
        request.setAttribute("billList", billList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/bill/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
