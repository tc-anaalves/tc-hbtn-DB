package entities;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_aluno")
public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "numero_matricula")
    private String matricula;

    @Column(name = "data_nascimento")
    @Temporal(TemporalType.DATE)
    private Date nascimento;

    @Column
    private String email;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private List<Telefone> telefones = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "aluno_id", referencedColumnName = "id")
    private List<Endereco> enderecos = new ArrayList<>();

    @Override
    public String toString() {
        return "Aluno {" +
                "id: " + id +
                ", Nome Completo: " + nomeCompleto  +
                ", Matricula: " + matricula +
                ", Data de Nascimento: " + convertDateToString(nascimento) +
                ", Email: " + email +
                '}';
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

}