package cotest.kim.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String name;

    @Embedded //내장 타입
    private Address address;

    @OneToMany(mappedBy = "member") //나는 맵핑된 거울일 뿐이다.
    private List<Order> orders = new ArrayList<>();

}
