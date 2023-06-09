package pe.todotic.bookstoreapi_s3.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SalesItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Float price;

    @Column(name = "downs_ava")
    private Integer downloadsAvailable;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;


    @ManyToOne
    @JoinColumn(name = "orden_id", referencedColumnName = "id")
    private SalesOrder order;

}
