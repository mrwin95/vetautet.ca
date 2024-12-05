package org.vetau.controller.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vetau.application.service.ticket.TicketDetailAppService;
import org.vetau.controller.model.vo.ResultMessage;
import org.vetau.domain.model.entity.TicketDetail;

@RestController
@RequestMapping("/api/v1/ticket")
@Slf4j
public class TicketDetailController {

    private final TicketDetailAppService ticketDetailAppService;

    public TicketDetailController(TicketDetailAppService ticketDetailAppService)
    {
        this.ticketDetailAppService = ticketDetailAppService;
    }

    public ResultMessage<TicketDetail> getTicketDetail(@PathVariable("ticketId") Long ticketId, @PathVariable("detailId") Long detailId){
        log.info("Params ticketId={}, detailId={}", ticketId, detailId);
        return null;// ResultUtil.data(ticketDetailAppService.getTicketDetailById(detailId));
    }
}
