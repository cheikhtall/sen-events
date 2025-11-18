package sn.dev.ct.application.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.dev.ct.core.domain.account.dto.AccountDTO;
import sn.dev.ct.core.domain.account.service.AccountService;
import sn.dev.ct.core.domain.client.dto.ClientDTO;
import sn.dev.ct.core.domain.client.service.ClientService;
import sn.dev.ct.core.domain.equipment.dto.CategoryDTO;
import sn.dev.ct.core.domain.equipment.dto.EquipmentDTO;
import sn.dev.ct.core.domain.equipment.handler.CategoryEquipmentHandler;
import sn.dev.ct.core.domain.equipment.service.CategoryService;
import sn.dev.ct.core.domain.equipment.service.EquipmentService;

import java.util.Set;

@RestController
@RequestMapping("/api/admin")
public class AdminApi {
    private final ClientService clientService;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final EquipmentService equipmentService;
    private final CategoryEquipmentHandler categoryEquipmentHandler;

    public AdminApi(ClientService clientService, AccountService accountService,
                    CategoryService categoryService, EquipmentService equipmentService,
                    CategoryEquipmentHandler categoryEquipmentHandler) {
        this.clientService = clientService;
        this.accountService = accountService;
        this.categoryService = categoryService;
        this.equipmentService = equipmentService;
        this.categoryEquipmentHandler = categoryEquipmentHandler;
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.retrieves(id));
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok("Client deleted");
    }

    @GetMapping(path = "/clients/list")
    public ResponseEntity<Page<ClientDTO>> findAllClients(
            @RequestParam(defaultValue = "0", name = "page")int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(defaultValue = "id", name = "sort") String sort
    ) {
        Pageable paging = PageRequest.of(page, size, Sort.by(sort).ascending());
        return ResponseEntity.ok(clientService.retrieve(paging));
    }

    @GetMapping(path ="/admin/accounts/list")
    public ResponseEntity<Page<AccountDTO>> findAllAccounts(
            @RequestParam(defaultValue = "0", name = "page")int page,
            @RequestParam(defaultValue = "5", name = "size") int size,
            @RequestParam(defaultValue = "id", name = "sort") String sort
    ){
        Pageable paging = PageRequest.of(page, size, Sort.by(sort).ascending());
        return ResponseEntity.ok(accountService.getAllAccounts(paging));
    }

    @PostMapping(path = "/admin/account/{id}/suspend")
    public ResponseEntity<String> suspendAccount(@PathVariable Long id) {
        accountService.suspendAccount(id);
        return ResponseEntity.ok("Account suspended");
    }

    @PostMapping(path = "/equipments")
    public ResponseEntity<EquipmentDTO> addEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        return ResponseEntity.ok(equipmentService.register(equipmentDTO));
    }

    @DeleteMapping(path = "/equipment/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable Long id) {
        equipmentService.delete(id);
        return ResponseEntity.ok("Equipment deleted");
    }

    @PostMapping(path = "/equipments/category")
    public ResponseEntity<CategoryDTO> addEquipmentCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.register(categoryDTO));
    }
    @DeleteMapping(path = "/equipment/category/{id}")
    public ResponseEntity<String> deleteEquipmentCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Category deleted");
    }

    @PostMapping(path = "/categories/{categoryId}/equipments/{equipmentId}")
    public ResponseEntity<CategoryDTO> addEquipmentToCategory(@PathVariable Long categoryId, @PathVariable Long equipmentId) {
        return ResponseEntity.ok(categoryEquipmentHandler.addEquipment(categoryId, equipmentId));
    }

    @PostMapping(path = "/categories/{id}/equipments")
    public ResponseEntity<CategoryDTO> addEquipmentToCategory(@PathVariable Long id, @RequestBody Set<Long> equipmentIds) {
        return ResponseEntity.ok(categoryEquipmentHandler.addEquipments(id, equipmentIds));
    }
    @PostMapping(path = "/equipments/{id}/categories")
    public ResponseEntity<EquipmentDTO> addCategoryToEquipment(@PathVariable Long id, @RequestBody Set<Long> categoryIds) {
        return ResponseEntity.ok(categoryEquipmentHandler.addCategories(id, categoryIds));
    }

    @DeleteMapping(path = "/categories/{categoryId}/equipments/{equipmentId}")
    public ResponseEntity<CategoryDTO> removeEquipmentToCategory(@PathVariable Long categoryId, @PathVariable Long equipmentId) {
        return ResponseEntity.ok(categoryEquipmentHandler.removeEquipment(categoryId, equipmentId));
    }

    @DeleteMapping(path = "/categories/{id}/equipments")
    public ResponseEntity<CategoryDTO> removeEquipmentsToCategory(@PathVariable Long id, @RequestBody Set<Long> equipmentIds) {
        return ResponseEntity.ok(categoryEquipmentHandler.removeEquipments(id, equipmentIds));
    }

    @DeleteMapping(path = "/equipments/{id}/categorie")
    public ResponseEntity<EquipmentDTO> removeCategoriesToEquipment(@PathVariable Long id, @RequestBody Set<Long> categoriesIds) {
        return ResponseEntity.ok(categoryEquipmentHandler.removeCategories(id, categoriesIds));
    }
}
