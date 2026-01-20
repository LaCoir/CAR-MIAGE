package com.example.car.tp1.ctrl;
import com.example.car.tp1.model.Client;
import com.example.car.tp1.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class ClientController {

    @Autowired // tiêm ClientService vào đây
    private ClientService clientService;

    @GetMapping("/login") //login page
    public ModelAndView loginPage() {
        var emptyClient = new Client();
        var modelData = Map.of("client", emptyClient);

        return new ModelAndView("login",modelData);
    }
    @PostMapping("/login")
    public ModelAndView handleLogin(@ModelAttribute Client client, HttpSession httpSession) {
        Client foundClient = clientService.findByEmailAndPassword(client.getEmail(), client.getPassword());
        if (foundClient != null) {
            httpSession.setAttribute("loggedIn", foundClient);
            return new ModelAndView("redirect:/home");
        } else {
            var modelData = Map.of("client", client, "error", "Invalid email or password");
            return new ModelAndView("login", modelData);
        }
    }


    @GetMapping("/home") // lấy danh sách client từ db gửi sang view để hiển thị
    public ModelAndView home(){
        var clients = clientService.readAllClients(); //list client
        var emptyClient = new Client(); // để binding form
        var modelData = Map.of("clients", clients, "newClient",emptyClient);
        return new ModelAndView("home", modelData);
    }

    @PostMapping("/home/save") // nhận dữ liệu từ form gửi lên để tạo client mới. xu ly cho GetMapping
    public String save(@ModelAttribute Client client) {
        clientService.createClient(client);
        return "redirect:/home"; // sau khi lưu xong quay về trang home
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/login";
    }
}