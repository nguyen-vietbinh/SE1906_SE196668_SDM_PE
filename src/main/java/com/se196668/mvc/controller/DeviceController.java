package com.se196668.mvc.controller;

import com.se196668.mvc.dto.DeviceDetailsDTO;
import com.se196668.mvc.dto.DeviceFormDTO;
import com.se196668.mvc.dto.DeviceListDTO;
import com.se196668.mvc.entity.Device;
import com.se196668.mvc.service.CategoryService;
import com.se196668.mvc.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/search"})
    public String getAll(@RequestParam(name = "deviceName", required = false) String deviceName, Model model){

        List<DeviceListDTO> result = deviceService.search(deviceName);

        if(result.isEmpty() && deviceName != null && !deviceName.isBlank()){
            model.addAttribute("errorMessage", "No records found");
        }else{
            model.addAttribute("deviceName", deviceName);
            model.addAttribute("cateList", categoryService.cateList());
            model.addAttribute("devices", result);
            model.addAttribute("statusList", deviceService.statusList());
        }
        return "list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes box){

        Device device = deviceService.findById(id);
        if(device.getStatus().equalsIgnoreCase("AC")){
            box.addFlashAttribute("errorMessage", "Cannot delete an active device");
            return "redirect:/";
        }else{
            deviceService.delete(id);
            box.addFlashAttribute("successMessage", "Deleted successfully");
        }
        return "redirect:/";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("device", new DeviceFormDTO());
        model.addAttribute("statusList", deviceService.statusList());
        model.addAttribute("cateList", categoryService.cateList());
        return "add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("device") DeviceFormDTO deviceFormDTO, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("statusList", deviceService.statusList());
            model.addAttribute("cateList", categoryService.cateList());
            return "add";
        }

        try{
            deviceService.create(deviceFormDTO);
        } catch (RuntimeException exception) {
            String message = exception.getMessage();

            if ("Device code can not be duplicate".equals(message)) {

                result.rejectValue("deviceCode", "duplicate", message);
            } else {

                model.addAttribute("errorMessage", message);
            }

            model.addAttribute("statusList", deviceService.statusList());
            model.addAttribute("cateList", categoryService.cateList());
            return "add";
        }

        model.addAttribute("successMessage", "Create new device successfully");
        model.addAttribute("device", new DeviceFormDTO());
        model.addAttribute("statusList", deviceService.statusList());
        model.addAttribute("cateList", categoryService.cateList());

        return "add";
    }

    @GetMapping("/detail/{id}")
    public String shoesDetail(@PathVariable("id") Integer id, Model model, RedirectAttributes errorBox){
        DeviceDetailsDTO deviceDetail = deviceService.getDetails(id);
        if(deviceDetail == null){
            errorBox.addFlashAttribute("errorMessage", "Can not show the details information");
            return "redirect:/";
        }else {
            model.addAttribute("deviceDetail", deviceDetail);
            model.addAttribute("statusList", deviceService.statusList());
            model.addAttribute("cateList", categoryService.cateList());
        }
        return "form-details";
    }
}
