package ru.alltime.dogovora.infrastructure.api.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/infra/web")
public class InfraTestWebController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return """
            <!doctype html>
            <html lang="ru">
              <head><meta charset="utf-8"><title>Infra Hello</title></head>
              <body>
                <h1>Привет 👋</h1>
                <p>Это простой HTML из инфраструктурного web-контроллера.</p>
                <ul>
                  <li><a href="/infra/api/health">/infra/api/health</a></li>
                  <li><a href="/infra/api/ping">/infra/api/ping</a></li>
                  <li>Пример: <code>/infra/api/users/1</code></li>
                </ul>
              </body>
            </html>
            """;
    }
}