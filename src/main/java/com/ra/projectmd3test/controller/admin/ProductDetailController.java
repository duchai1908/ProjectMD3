package com.ra.projectmd3test.controller.admin;

import com.ra.projectmd3test.model.dto.ProductDetailRequest;
import com.ra.projectmd3test.model.entity.Image;
import com.ra.projectmd3test.model.entity.ProductDetail;
import com.ra.projectmd3test.service.design.IColorService;
import com.ra.projectmd3test.service.design.IProductDetailService;
import com.ra.projectmd3test.service.design.ISizeService;
import com.ra.projectmd3test.service.design.IimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/productdetail")
public class ProductDetailController {
    @Autowired
    private ISizeService sizeService;
    @Autowired
    private IColorService colorService;
    @Autowired
    private IProductDetailService productDetailService;
    @Autowired
    private IimageService imageService;
    @GetMapping("/list/{id}")
    public String list(Model model, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "5") Integer size, @PathVariable("id") Integer id) {
        Integer pageSize = Math.max(size,1);
        Integer currentPage  = Math.max(page,0);
        Long totalProductDetail = productDetailService.getTotalProductDetail();
        int totalPages = (int) Math.ceil((double) totalProductDetail / pageSize);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sizeService",sizeService.findAll());
        model.addAttribute("colorService",colorService.findAll());
        model.addAttribute("viewName","list-productdetail");
        model.addAttribute("portfolio","ProductDetail");
        model.addAttribute("action","List");
        model.addAttribute("productId",id);
        model.addAttribute("listProductDetail",productDetailService.getProductDetailByProductId(id,currentPage,pageSize));
        model.addAttribute("productDetailRequest", new ProductDetailRequest());
        model.addAttribute("listImage",imageService.findAll());
        return "admin/dashboard";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("productDetailRequest") ProductDetailRequest productDetailRequest, BindingResult bindingResult,Model model,@RequestParam("image") List<MultipartFile> images) {
                    if(bindingResult.hasErrors()) {
                        model.addAttribute("sizeService",sizeService.findAll());
                        model.addAttribute("colorService",colorService.findAll());
                        model.addAttribute("viewName","list-productdetail");
                        model.addAttribute("portfolio","ProductDetail");
                        model.addAttribute("action","List");
                        model.addAttribute("productId",productDetailRequest.getProduct());
                        model.addAttribute("productDetailRequest", new ProductDetailRequest());
                        return "admin/dashboard";
                    }else{
                        productDetailService.saveProductDetail(productDetailRequest,images);
                        return "redirect:/admin/productdetail/list/"+productDetailRequest.getProduct();
                    }
    }
}
