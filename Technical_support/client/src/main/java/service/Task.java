
package service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for task complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="task">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateOfCreation" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reportFromSupport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reporterEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reporterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://service/}taskStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "task", propOrder = {
    "dateOfCreation",
    "description",
    "id",
    "reportFromSupport",
    "reporterEmail",
    "reporterName",
    "status"
})
public class Task {

    protected Long dateOfCreation;
    protected String description;
    protected int id;
    protected String reportFromSupport;
    protected String reporterEmail;
    protected String reporterName;
    @XmlSchemaType(name = "string")
    protected TaskStatus status;

    /**
     * Gets the value of the dateOfCreation property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDateOfCreation() {
        return dateOfCreation;
    }

    /**
     * Sets the value of the dateOfCreation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDateOfCreation(Long value) {
        this.dateOfCreation = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the reportFromSupport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReportFromSupport() {
        return reportFromSupport;
    }

    /**
     * Sets the value of the reportFromSupport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReportFromSupport(String value) {
        this.reportFromSupport = value;
    }

    /**
     * Gets the value of the reporterEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReporterEmail() {
        return reporterEmail;
    }

    /**
     * Sets the value of the reporterEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReporterEmail(String value) {
        this.reporterEmail = value;
    }

    /**
     * Gets the value of the reporterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReporterName() {
        return reporterName;
    }

    /**
     * Sets the value of the reporterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReporterName(String value) {
        this.reporterName = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link TaskStatus }
     *     
     */
    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaskStatus }
     *     
     */
    public void setStatus(TaskStatus value) {
        this.status = value;
    }

}
