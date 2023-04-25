package com.example.springcashier;


import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/")
public class SpringCashierController {

    @Autowired
    private CashierOrderService orderService;

    @GetMapping
    public String getAction( @ModelAttribute("command") Command command, 
                            Model model, HttpSession session) {

        String message = "" ;

        command.setRegister( "5012349" ) ;
        message = "Starbucks Reserved Order" + "\n\n" +
            "Register: " + command.getRegister() + "\n" +
            "Status:   " + "Ready for New Order"+ "\n" ;

        String server_ip = "" ;
        String host_name = "" ;
        try { 
            InetAddress ip = InetAddress.getLocalHost() ;
            server_ip = ip.getHostAddress() ;
            host_name = ip.getHostName() ;
        } catch (Exception e) { }

        model.addAttribute( "message", message ) ;
        model.addAttribute( "server",  host_name + "/" + server_ip ) ;

        String selectedStore = (String) session.getAttribute("store");
        if (selectedStore != null) {
            command.setStores(selectedStore);
        }

        return "starbucks" ;

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping
    public String postAction(@Valid @ModelAttribute("command") Command command,
        @RequestParam(value = "action", required = true) String action,
        Errors errors, Model model, HttpServletRequest request) {

        String message = "";

        log.info("Action: " + action);
        command.setRegister(command.getStores());
        log.info("Command: " + command);

        /* Process Post Action */
        if (action.equals("Place Order")) {
            CashierOrder order = CashierOrder.GetNewOrder();
            order.setRegister(command.getRegister());
            order = orderService.createOrder(order); // Save the new order in the database
            message = "Starbucks Reserved Order" + "\n\n" +
                    "Drink: " + order.getDrink() + "\n" +
                    "Milk:  " + order.getMilk() + "\n" +
                    "Size:  " + order.getSize() + "\n" +
                    "Total: " + order.getTotal() + "\n" +
                    "\n" +
                    "Register: " + order.getRegister() + "\n" +
                    "Status:   " + order.getStatus() + "\n";
        } else if (action.equals("Get Order")) {
            // Retrieve the latest order from the database
            List<CashierOrder> orders = orderService.getAllOrders();
            CashierOrder latestOrder = orders.isEmpty() ? null : orders.get(orders.size() - 1);
            HttpSession session = request.getSession();
            session.setAttribute("store", command.getStores());
            if (latestOrder != null) {
                message = "Starbucks Reserved Order" + "\n\n" +
                        "Drink: " + latestOrder.getDrink() + "\n" +
                        "Milk:  " + latestOrder.getMilk() + "\n" +
                        "Size:  " + latestOrder.getSize() + "\n" +
                        "Total: " + latestOrder.getTotal() + "\n" +
                        "\n" +
                        "Register: " + latestOrder.getRegister() + "\n" +
                        "Status:   " + latestOrder.getStatus() + "\n";
            } else {
                message = "No order found!";
            }
        } else if (action.equals("Clear Order")) {
            // Delete the latest order from the database
            List<CashierOrder> orders = orderService.getAllOrders();
            if (!orders.isEmpty()) {
                orderService.deleteOrder(orders.get(orders.size() - 1).getId());
            }
            message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + command.getRegister() + "\n" +
                    "Status:   " + "Ready for New Order" + "\n";
        }
        command.setMessage(message);

        String server_ip = "";
        String host_name = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            server_ip = ip.getHostAddress();
            host_name = ip.getHostName();
        } catch (Exception e) {
        }

        model.addAttribute("message", message);
        model.addAttribute("server", host_name + "/" + server_ip);

        return "starbucks";


    }

    @PostMapping("/selectStore")
    public String selectStore(@RequestParam String store, HttpSession session) {
    session.setAttribute("store", store);
    return "redirect:/";
    }

    public String placeOrder(@ModelAttribute("order") CashierOrder order, HttpSession session) {
        String store = (String) session.getAttribute("store");
        order.setStore(store);
        orderService.createOrder(order);
        return "redirect:/";
    }


 

    
}
    



