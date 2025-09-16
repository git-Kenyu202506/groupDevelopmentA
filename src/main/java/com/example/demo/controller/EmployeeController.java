package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;


//http://localhost:8080/employeesを入力すると検索ページに遷移する。


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // 検索ページ
    @GetMapping
    public String searchPage() {
        return "employeeSearch";
    }

    // 検索処理
    @PostMapping("/search")
    public String search(
            @RequestParam(required = false) Integer employeeId,
            @RequestParam(required = false) String employeeName,
            @RequestParam(required = false) Integer ageFrom,
            @RequestParam(required = false) Integer ageTo,
            @RequestParam(required = false) String startDateFrom,
            @RequestParam(required = false) String startDateTo,
            @RequestParam(required = false) String endDateFrom,
            @RequestParam(required = false) String endDateTo,
            Model model
    ) {
        if(ageFrom != null && ageTo != null && ageFrom > ageTo){
            model.addAttribute("rangeError", "年齢範囲が不正です");
        }
        if(startDateFrom != null && startDateTo != null && startDateFrom.compareTo(startDateTo) > 0){
            model.addAttribute("rangeError", "入社日範囲が不正です");
        }
        if(endDateFrom != null && endDateTo != null && endDateFrom.compareTo(endDateTo) > 0){
            model.addAttribute("rangeError", "退職日範囲が不正です");
        }

        List<Employee> result = service.searchEmployees(employeeId, employeeName, ageFrom, ageTo, startDateFrom, startDateTo, endDateFrom, endDateTo);
        model.addAttribute("employees", result);
        model.addAttribute("count", result.size());

        // 検索条件をモデルに追加
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("employeeName", employeeName);
        model.addAttribute("ageFrom", ageFrom);
        model.addAttribute("ageTo", ageTo);
        model.addAttribute("startDateFrom", startDateFrom);
        model.addAttribute("startDateTo", startDateTo);
        model.addAttribute("endDateFrom", endDateFrom);
        model.addAttribute("endDateTo", endDateTo);

        return "employeeSearch";
    }

    // IDクリックで更新ページ
    @GetMapping("/update/{id}")
    public String showEmployee(@PathVariable("id") Integer id, Model model) {
        Employee emp = service.findById(id);
        model.addAttribute("employee", emp);
        return "employeeUpdate"; 
    }


    // 削除ページ
    @PostMapping("/delete")
    public String deleteEmployees(
            @RequestParam(name = "employeeIds", required = false) List<Integer> employeeIds,
            Model model
    ) {
        if (employeeIds == null || employeeIds.isEmpty()) {
            // チェックなし → そのまま削除ページへ遷移
            model.addAttribute("ids", List.of()); 
        } else {
            // チェックあり → 削除対象IDを渡す
            model.addAttribute("ids", employeeIds);
        }
        return "employeeDelete"; 
    }

    // メニュー
    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    // 登録ページ
    @GetMapping("/insert")
    public String insert() {
        return "insert";
    }
    


    // メニューから直接削除ページへ
    @GetMapping("/delete")
    public String showDeletePage() {
        return "employeeDelete";
    }  // メニューから直接更新ページへ
    
    @GetMapping("/update")
    public String showUpdatePage(Model model) {
        model.addAttribute("employee", null); 
        return "employeeUpdate"; 
    }

    
    
    
}

