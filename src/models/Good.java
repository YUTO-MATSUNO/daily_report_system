package models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "goods")
@NamedQueries({
    @NamedQuery(
            name = "getGoodsCount",
            query = "SELECT COUNT(g) FROM Good AS g WHERE g.report = :report"
        ),
    @NamedQuery(
            name = "checkRegisteredEmployee",
            query = "SELECT COUNT(g) FROM Good AS g WHERE g.employee = :employee AND g.report = :report"   //いいねした人が登録されているか確認
        ),
    @NamedQuery(
            name = "checkRegisteredGood",
            query = "SELECT g FROM Good AS g WHERE g.employee = :employee AND g.report = :report"   //特定のいいねを検索
        ),
})
@Entity
public class Good implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }


}