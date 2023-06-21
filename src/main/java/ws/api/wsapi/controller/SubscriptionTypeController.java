package ws.api.wsapi.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.api.wsapi.dto.model.SubscriptionTypeDTO;
import ws.api.wsapi.model.jpa.SubscriptionType;
import ws.api.wsapi.service.SubscriptionTypeService;

import java.util.List;

@RestController
@RequestMapping("/sub-tp")
@RequiredArgsConstructor
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;
    @GetMapping("/all-sub")
    public ResponseEntity<List<SubscriptionType>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.finAll());
    }
    @GetMapping("/sub-byId/{id}")
    public ResponseEntity<SubscriptionType> findSubById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.findById(id));
    }
    @PostMapping
    public ResponseEntity<SubscriptionType> addSubscriptionType(@Valid @RequestBody SubscriptionTypeDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionTypeService.create(dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        subscriptionTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("deletado");
    }
    @PutMapping("/up/{id}")
    public ResponseEntity<SubscriptionType> update(@Valid @PathVariable Long id,@RequestBody SubscriptionTypeDTO subscriptionTypeDTO){
        return ResponseEntity.status(HttpStatus.OK).body(subscriptionTypeService.update(id,subscriptionTypeDTO));
    }
}
