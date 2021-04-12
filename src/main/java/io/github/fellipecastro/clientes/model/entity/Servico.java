package io.github.fellipecastro.clientes.model.entity;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Servico {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false, length = 150)
  private String descricao;

  @ManyToOne
  @JoinColumn(name = "id_cliente")
  private Cliente cliente;

  @Column
  private BigDecimal valor;
}
