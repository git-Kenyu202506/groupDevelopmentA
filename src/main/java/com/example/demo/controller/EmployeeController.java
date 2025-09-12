

package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
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

    private LocalDate parseDateStrict(String value, String fieldName, Model model) {
        if (value == null || value.isEmpty()) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu/MM/dd")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDate.parse(value, formatter);
        } catch (DateTimeParseException e) {
            model.addAttribute("inputError", fieldName + " は存在する日付 (yyyy/MM/dd) で入力してください");
            return null;
        }
    }
    @GetMapping
	public String searchPage() {
		return "employeeSearch";
	}

    @PostMapping("/search")
    public String search(
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) String employeeName,
            @RequestParam(required = false) String ageFrom,
            @RequestParam(required = false) String ageTo,
            @RequestParam(required = false) String startDateFrom,
            @RequestParam(required = false) String startDateTo,
            @RequestParam(required = false) String endDateFrom,
            @RequestParam(required = false) String endDateTo,
            Model model) {

        Integer empId = null;
        Integer ageF = null;
        Integer ageT = null;

        try { if(employeeId!=null && !employeeId.isEmpty()) empId = Integer.parseInt(employeeId); } 
        catch(NumberFormatException e){ model.addAttribute("inputError","社員IDは数値で入力してください"); }

        try { if(ageFrom!=null && !ageFrom.isEmpty()) ageF = Integer.parseInt(ageFrom); } 
        catch(NumberFormatException e){ model.addAttribute("inputError","年齢は数値で入力してください"); }

        try { if(ageTo!=null && !ageTo.isEmpty()) ageT = Integer.parseInt(ageTo); } 
        catch(NumberFormatException e){ model.addAttribute("inputError","年齢は数値で入力してください"); }

        // HTML dateの yyyy-MM-dd を LocalDate に変換
        LocalDate sFrom = (startDateFrom==null||startDateFrom.isEmpty()) ? null : LocalDate.parse(startDateFrom);
        LocalDate sTo   = (startDateTo==null||startDateTo.isEmpty()) ? null : LocalDate.parse(startDateTo);
        LocalDate eFrom = (endDateFrom==null||endDateFrom.isEmpty()) ? null : LocalDate.parse(endDateFrom);
        LocalDate eTo   = (endDateTo==null||endDateTo.isEmpty()) ? null : LocalDate.parse(endDateTo);
        
        
        // HTML text用
//        LocalDate sFrom = parseDateStrict(startDateFrom, "入社日(From)", model);
//        LocalDate sTo   = parseDateStrict(startDateTo, "入社日(To)", model);
//        LocalDate eFrom = parseDateStrict(endDateFrom, "退社日(From)", model);
//        LocalDate eTo   = parseDateStrict(endDateTo, "退社日(To)", model);

//        if (sFrom!=null && sTo!=null && sFrom.isAfter(sTo)) {
//            model.addAttribute("rangeError","入社日範囲が不正です");
//        }
//        if (eFrom!=null && eTo!=null && eFrom.isAfter(eTo)) {
//            model.addAttribute("rangeError","退職日範囲が不正です");
//        }
        

        if(ageF != null && ageT != null && ageF>ageT) model.addAttribute("rangeError","年齢範囲が不正です");
        if(sFrom!=null && sTo!=null && sFrom.isAfter(sTo)) model.addAttribute("rangeError","入社日範囲が不正です");
        if(eFrom!=null && eTo!=null && eFrom.isAfter(eTo)) model.addAttribute("rangeError","退職日範囲が不正です");

        List<Employee> result = service.searchEmployees(empId, employeeName, ageF, ageT, sFrom, sTo, eFrom, eTo);
        model.addAttribute("employees", result);
        model.addAttribute("count", result.size());

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
		return "employeeUpdate_dammy";
	}

	// 削除ページ
	@PostMapping("/delete")
	public String deleteEmployees(
			@RequestParam(name = "employeeIds", required = false) List<Integer> employeeIds,
			Model model) {
		if (employeeIds == null || employeeIds.isEmpty()) {
			// チェックなし → そのまま削除ページへ遷移
			model.addAttribute("ids", List.of());
		} else {
			// チェックあり → 削除対象IDを渡す
			model.addAttribute("ids", employeeIds);
		}
		return "employeeDelete_dammy";
	}

	// メニュー
	@GetMapping("/menu")
	public String menu() {
		return "menu_dammy";
	}

	// 登録ページ
	@GetMapping("/insert")
	public String insert() {
		return "insert_dammy";
	}

	// メニューから直接削除ページへ
	@GetMapping("/delete")
	public String showDeletePage() {
		return "employeeDelete_dammy";
	} // メニューから直接更新ページへ

	@GetMapping("/update")
	public String showUpdatePage(Model model) {
		model.addAttribute("employee", null);
		return "employeeUpdate_dammy";
	}
	
	
	

}

