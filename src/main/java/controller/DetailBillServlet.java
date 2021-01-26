package controller;

import model.Bill;
import model.Cake;
import model.Customer;
import model.DetailBill;
import service.DetailBillService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet(name = "DetailBillServlet", urlPatterns = "/detailBillServlet")
public class DetailBillServlet extends HttpServlet {
    private DetailBillService detailBillService = new DetailBillService();
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
                addDetailBill(request, response);
                break;
            case "edit":
                updateDetailBill(request, response);
                break;
            case "delete":
                deleteDetailBill(request, response);
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
                listDetailBill(request, response);
        }
    }

    //Delete
    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        DetailBill detailBill = detailBillService.selectDetailBill(id);
        if(detailBill!=null){
            request.setAttribute("detailBill", detailBill);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/detailBill/delete.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void deleteDetailBill(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        DetailBill detailBill = detailBillService.selectDetailBill(id);
        RequestDispatcher dispatcher;
        if(detailBill != null){
            try {
                detailBillService.deleteDetailBill(id);
                dispatcher = request.getRequestDispatcher("admin/detailBill/delete.jsp");
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
        DetailBill detailBill = detailBillService.selectDetailBill(id);
        if(detailBill!=null){
            request.setAttribute("detailBill", detailBill);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/detailBill/edit.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateDetailBill(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int billId = Integer.parseInt(request.getParameter("billId"));
        int cakeId = Integer.parseInt(request.getParameter("cakeId"));
        int billQuantity = Integer.parseInt(request.getParameter("billQuantity"));
        int billPrice = Integer.parseInt(request.getParameter("billPrice"));

        Bill bill = detailBillService.findBillById(billId);
        Cake cake = detailBillService.findCakeById(cakeId);
        DetailBill detailBill = new DetailBill(bill, cake, billQuantity, billPrice,id);
        try {
            detailBillService.updateDetailBill(detailBill);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/detailBill/edit.jsp");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/detailBill/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDetailBill (HttpServletRequest request, HttpServletResponse response){
        int billId = Integer.parseInt(request.getParameter("billId"));
        int cakeId = Integer.parseInt(request.getParameter("cakeId"));
        int billQuantity = Integer.parseInt(request.getParameter("billQuantity"));
        int billPrice = Integer.parseInt(request.getParameter("billPrice"));

        Bill bill = detailBillService.findBillById(billId);
        Cake cake = detailBillService.findCakeById(cakeId);
        DetailBill detailBill = new DetailBill(bill, cake, billQuantity, billPrice, null);
        try {
            detailBillService.insertDetailBill(detailBill);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/detailBill/create.jsp");
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
    private void listDetailBill(HttpServletRequest request, HttpServletResponse response) {
        List<DetailBill> detailBillList = detailBillService.selectAllDetailBill();
        request.setAttribute("detailBillList", detailBillList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/detailBill/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
