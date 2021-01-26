package controller;

import model.Cake;
import model.Category;
import model.Customer;
import service.CakeService;
import service.CategoryService;
import service.CustomerService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "CakeServlet", urlPatterns = "/cakeServlet")
public class CakeServlet extends HttpServlet {
    private CakeService cakeService = new CakeService();
    private CustomerService customerService = new CustomerService();
    private CategoryService category = new CategoryService();
    List<Category> categoryList = category.selectAllCategorys();
    NumberFormat nf = NumberFormat.getInstance(new Locale("en", "US"));
//    nf.format(cake.getPrice())


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
                addCake(request, response);
                break;
            case "edit":
                updateCake(request, response);
                break;
            case "delete":
                deleteCake(request, response);
                break;
            case "buy":
                updateCakeWhenBuy(request, response);
                break;
            case "search":
                searchCake(request, response);
                break;
            case "client":
                showClient(request, response);
                break;
            case "login":
                login(request, response);
                break;
            default:
                listCake(request, response);
        }
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
//                showCreateForm(request, response);
                break;
            case "edit":
//                showEditForm(request, response);
                break;
            case "delete":
//                showDeleteForm(request, response);
                break;
            case "list":
                listCake(request, response);
                break;
            default:
                showClient(request, response);
        }
    }


    //Delete
//    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Cake cake = cakeService.selectCake(id);
//        if (cake != null) {
//            request.setAttribute("cake", cake);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/cake/list.jsp");
//            try {
//                dispatcher.forward(request, response);
//            } catch (ServletException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void deleteCake(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));

        System.out.println(id);
        Cake cake = cakeService.selectCake(id);
        RequestDispatcher dispatcher;
        if (cake != null) {
            try {
                cakeService.deleteCake(id);
                dispatcher = request.getRequestDispatcher("/cakeServlet?action=list");
                request.setAttribute("message", "Delete thanh cong");
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
    }


    // Update
//    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Cake cake = cakeService.selectCake(id);
//        Date date = new Date();
//        if (cake != null) {
//            request.setAttribute("date", date);
//            request.setAttribute("cake", cake);
//            request.setAttribute("categoryList", categoryList);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("admin/cake/edit.jsp");
//            try {
//                dispatcher.forward(request, response);
//            } catch (ServletException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    private void updateCake(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String size = request.getParameter("size");
        Timestamp expireDate = Timestamp.valueOf(request.getParameter("expireDate"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("price"));
        System.out.println(price);
        String image = request.getParameter("image");
        String categoryName = request.getParameter("category");
        Category category = new Category(categoryName);
        Cake cake = new Cake(id, name, size, expireDate, quantity, price, image, category);
        try {
            cakeService.updateCake(cake);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cakeServlet?action=list");
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


    //    Buy
    private void updateCakeWhenBuy(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Cake cake = cakeService.findCakeById(id);
        int quantity2 = (cake.getQuantity() - quantity);



        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Customer customer = new Customer(null, name, phone, address);
        try {
            if (cake.getQuantity() < quantity) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cakeServlet?action=client");
                request.setAttribute("message", "Out of range");
                dispatcher.forward(request, response);
            } else {
                cakeService.updateCakeWhenBuy(cake, quantity2);
                customerService.insertCustomer(customer);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cakeServlet?action=client");
                request.setAttribute("message", "Buy thanh cong");
                dispatcher.forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //    Create
//    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
//        request.setAttribute("categoryList", categoryList);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/cake/create.jsp");
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void addCake(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String size = request.getParameter("size");
        Timestamp expireDate = Timestamp.valueOf(request.getParameter("expireDate"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int price = Integer.parseInt(request.getParameter("price"));
        String image = request.getParameter("image");
        String categoryName = request.getParameter("category");
        Category category = new Category(categoryName);
        Cake cake = new Cake(null, name, size, expireDate, quantity, price, image, category);
        try {
            cakeService.insertCake(cake);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cakeServlet?action=list");
            request.setAttribute("message", "Add thanh cong");
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
    private void listCake(HttpServletRequest request, HttpServletResponse response) {
        List<Cake> cakeList = cakeService.selectAllCakes();
        Date date = new Date();
        request.setAttribute("date", date);
        request.setAttribute("cakeList", cakeList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("nf", nf);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/cake/list.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showClient(HttpServletRequest request, HttpServletResponse response) {
        List<Cake> cakeList = cakeService.selectAllCakes();
        request.setAttribute("cakeList", cakeList);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("nf", nf);

        RequestDispatcher dispatcher = request.getRequestDispatcher("client.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //search
    private void searchCake(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("search");
        List<Cake> searchList = cakeService.searchCake(search);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
        request.setAttribute("searchList", searchList);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
        if(username.equals("admin") && password.equals("admin")){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cakeServlet?action=list");
                dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            request.setAttribute("message", "Moi nhap lai");
            dispatcher.forward(request, response);
        }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


