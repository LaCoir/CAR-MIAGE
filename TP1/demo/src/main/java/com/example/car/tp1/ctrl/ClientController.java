package com.example.car.tp1.ctrl;
import com.example.car.tp1.model.Client;
import com.example.car.tp1.service.ClientService;
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


    @GetMapping("/home") // lấy danh sách client từ db gửi sang view để hiển thị
    public ModelAndView home(){
        var clients = clientService.readAllClients(); //list client
        var emptyClient = new Client(); // để binding form
        var modelData = Map.of("clients", clients, "newClient",emptyClient);
        return new ModelAndView("home", modelData);
    }

    @PostMapping("/home/save") // nhận dữ liệu từ form gửi lên để tạo client mới
    public String save(@ModelAttribute Client client) {
        clientService.createClient(client);
        return "redirect:/home"; // sau khi lưu xong quay về trang /client
    }
}