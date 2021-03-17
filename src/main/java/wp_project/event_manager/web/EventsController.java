package wp_project.event_manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wp_project.event_manager.model.Event;
import wp_project.event_manager.model.Location;
import wp_project.event_manager.model.User;
import wp_project.event_manager.service.EventService;
import wp_project.event_manager.service.LocationService;
import wp_project.event_manager.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EventsController {

    private final EventService eventService;
    private final LocationService locationService;
    private final UserService userService;

    public EventsController(EventService eventService, LocationService locationService, UserService userService) {
        this.eventService = eventService;
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping({"/", "/events"})
    public String showEvents(@RequestParam(required = false) String nameSearch,
                             @RequestParam(required = false) Long locationId,
                             @RequestParam(required = false) Date time,
                             Model model) {
        List<Event> events;
        List<Location> locations;

        events = this.eventService.listEventsByNameAndLocationAndTime(nameSearch, locationId, time);
        locations = this.locationService.listAll();
        model.addAttribute("events", events);
        model.addAttribute("locations", locations);
        return "list.html";
    }

    @GetMapping("/events/create")
    public String showCreate(Model model) {
        List<Location> locations = this.locationService.listAll();
        List<User> users = this.userService.listAll();
        model.addAttribute("locations", locations);
        model.addAttribute("users", users);
        return "form.html";
    }

    @GetMapping("/events/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Event event = this.eventService.findById(id);

        List<Location> locations = this.locationService.listAll();
        List<User> users = this.userService.listAll();

        model.addAttribute("event", event);
        model.addAttribute("locations", locations);
        model.addAttribute("users", users);
        return "form.html";
    }

    @PostMapping("/events")
    public String create(@RequestParam String name,
                         @RequestParam List<String> attendeeUsernames,
                         @RequestParam Long locationId,
                         @RequestParam Date time) {
        this.eventService.create(name, attendeeUsernames, locationId, time, new ArrayList<>());
        return "redirect:/events";
    }

    @PostMapping("/events/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam List<String> attendeeUsernames,
                         @RequestParam Long locationId,
                         @RequestParam Date time,
                         @RequestParam List<Long> commentIds) {
        this.eventService.update(id, name, attendeeUsernames, locationId, time, commentIds);
        return "redirect:/events";
    }

    @PostMapping("/events/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.eventService.delete(id);
        return "redirect:/events";
    }
}
