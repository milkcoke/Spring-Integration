package spring.core.beans;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {
  private final MessageService messageService;

  @GetMapping("")
  public MessageResponse sendMessage () {
    return new MessageResponse(
      messageService.getServiceType(),
      messageService.getMessage()
    );
  }

  public record MessageResponse(String serviceType, String message) { }
}
