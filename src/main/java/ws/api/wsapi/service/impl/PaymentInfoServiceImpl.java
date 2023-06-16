package ws.api.wsapi.service.impl;

import org.springframework.stereotype.Service;
import ws.api.wsapi.dto.PaymentProcessDto;
import ws.api.wsapi.dto.consumers.CostumerDto;
import ws.api.wsapi.dto.consumers.OrderDto;
import ws.api.wsapi.dto.consumers.PaymentDto;
import ws.api.wsapi.dto.model.UserPaymentInfoDto;
import ws.api.wsapi.exception.BusinessException;
import ws.api.wsapi.exception.NotFoundException;
import ws.api.wsapi.integration.MailIntegration;
import ws.api.wsapi.integration.RaspayFeignClient;
import ws.api.wsapi.mapper.UserPaymentInfoMapper;
import ws.api.wsapi.mapper.wsraspay.CreditCardMapper;
import ws.api.wsapi.mapper.wsraspay.CustomerMapper;
import ws.api.wsapi.mapper.wsraspay.OrderMapper;
import ws.api.wsapi.mapper.wsraspay.PaymentMapper;
import ws.api.wsapi.model.UserPaymentInfo;
import ws.api.wsapi.repositories.UserPaymentInfoRepository;
import ws.api.wsapi.repositories.UserRepository;
import ws.api.wsapi.service.PaymentInfoService;

import java.util.Objects;
@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {
    private final UserRepository userRepository;
    private final UserPaymentInfoRepository paymentInfoRepository;
    private final RaspayFeignClient raspayFeignClient;
    private final MailIntegration mailIntegration;
    public PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository paymentInfoRepository, RaspayFeignClient raspayFeignClient, MailIntegration mailIntegration) {
        this.userRepository = userRepository;
        this.paymentInfoRepository = paymentInfoRepository;
        this.raspayFeignClient = raspayFeignClient;
        this.mailIntegration = mailIntegration;
    }
    @Override
    public boolean process(PaymentProcessDto dto) {
        //verificar se o usuário ja possui assinatura
        var userId = dto.getUserPaymentInfoDto().getUser();
        var userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()){
            throw new NotFoundException("usuário não encontrado");
        }
        var user = userOpt.get();
        if (Objects.nonNull(user.getSubscriptionsType())){
            throw new BusinessException("usuário já possuiu uma assinatura");
        }
        //cria e atualizar usuario raspay
        CostumerDto costumerDto = raspayFeignClient.createCostumer(CustomerMapper.build(user));
        //cria o pedido e pagamento
        OrderDto orderDto = raspayFeignClient.createOrder(OrderMapper
                .build(costumerDto.getId(),dto));
        //processar o pagamento
        PaymentDto paymentDto = PaymentMapper
                .build(costumerDto.getId(), orderDto.getId(),
                        CreditCardMapper.build(dto.getUserPaymentInfoDto(),user.getCpf()));

        Boolean sucessPayment = raspayFeignClient.processPayment(paymentDto);
        //salvar informações de pagamento do usuário
        //enviar o email
        //retornar sucesso ou não pagemento
        if (sucessPayment) {
            UserPaymentInfo paymentInfo = UserPaymentInfoMapper
                                            .fromDtoToEntity(dto.getUserPaymentInfoDto(), user);
            paymentInfoRepository.save(paymentInfo);
            mailIntegration.send(
                            user.getEmail(),
                       "teste para cardiaco",
                     "acesso liberado");
        }


        return true;
    }
}
