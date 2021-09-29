package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.annotations.BelongsTo;
import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the Medicine type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "Medicines")
@Index(name = "byMid", fields = {"userId"})
public final class Medicine implements Model {
  public static final QueryField ID = field("Medicine", "id");
  public static final QueryField NAME = field("Medicine", "name");
  public static final QueryField AVAILABLE_TABLETS = field("Medicine", "availableTablets");
  public static final QueryField DOSAGE = field("Medicine", "dosage");
  public static final QueryField REQUIRED_TIMES = field("Medicine", "requiredTimes");
  public static final QueryField EXPIRATION_DATE = field("Medicine", "expirationDate");
  public static final QueryField USER = field("Medicine", "userId");
  public static final QueryField TIMES = field("Medicine", "times");
  public static final QueryField DATES = field("Medicine", "dates");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Int") Integer availableTablets;
  private final @ModelField(targetType="Int") Integer dosage;
  private final @ModelField(targetType="Int") Integer requiredTimes;
  private final @ModelField(targetType="String") String expirationDate;
  private final @ModelField(targetType="User") @BelongsTo(targetName = "userId", type = User.class) User user;
  private final @ModelField(targetType="String", isRequired = true) List<String> times;
  private final @ModelField(targetType="String", isRequired = true) List<String> dates;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Integer getAvailableTablets() {
      return availableTablets;
  }
  
  public Integer getDosage() {
      return dosage;
  }
  
  public Integer getRequiredTimes() {
      return requiredTimes;
  }
  
  public String getExpirationDate() {
      return expirationDate;
  }
  
  public User getUser() {
      return user;
  }
  
  public List<String> getTimes() {
      return times;
  }
  
  public List<String> getDates() {
      return dates;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Medicine(String id, String name, Integer availableTablets, Integer dosage, Integer requiredTimes, String expirationDate, User user, List<String> times, List<String> dates) {
    this.id = id;
    this.name = name;
    this.availableTablets = availableTablets;
    this.dosage = dosage;
    this.requiredTimes = requiredTimes;
    this.expirationDate = expirationDate;
    this.user = user;
    this.times = times;
    this.dates = dates;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Medicine medicine = (Medicine) obj;
      return ObjectsCompat.equals(getId(), medicine.getId()) &&
              ObjectsCompat.equals(getName(), medicine.getName()) &&
              ObjectsCompat.equals(getAvailableTablets(), medicine.getAvailableTablets()) &&
              ObjectsCompat.equals(getDosage(), medicine.getDosage()) &&
              ObjectsCompat.equals(getRequiredTimes(), medicine.getRequiredTimes()) &&
              ObjectsCompat.equals(getExpirationDate(), medicine.getExpirationDate()) &&
              ObjectsCompat.equals(getUser(), medicine.getUser()) &&
              ObjectsCompat.equals(getTimes(), medicine.getTimes()) &&
              ObjectsCompat.equals(getDates(), medicine.getDates()) &&
              ObjectsCompat.equals(getCreatedAt(), medicine.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), medicine.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getAvailableTablets())
      .append(getDosage())
      .append(getRequiredTimes())
      .append(getExpirationDate())
      .append(getUser())
      .append(getTimes())
      .append(getDates())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("Medicine {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("name=" + String.valueOf(getName()) + ", ")
      .append("availableTablets=" + String.valueOf(getAvailableTablets()) + ", ")
      .append("dosage=" + String.valueOf(getDosage()) + ", ")
      .append("requiredTimes=" + String.valueOf(getRequiredTimes()) + ", ")
      .append("expirationDate=" + String.valueOf(getExpirationDate()) + ", ")
      .append("user=" + String.valueOf(getUser()) + ", ")
      .append("times=" + String.valueOf(getTimes()) + ", ")
      .append("dates=" + String.valueOf(getDates()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static NameStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static Medicine justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new Medicine(
      id,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null
    );
  }
  

  public interface NameStep {
    TimesStep name(String name);
  }
  

  public interface TimesStep {
    DatesStep times(List<String> times);
  }
  

  public interface DatesStep {
    BuildStep dates(List<String> dates);
  }
  

  public interface BuildStep {
    Medicine build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep availableTablets(Integer availableTablets);
    BuildStep dosage(Integer dosage);
    BuildStep requiredTimes(Integer requiredTimes);
    BuildStep expirationDate(String expirationDate);
    BuildStep user(User user);
  }
  

  public static class Builder implements NameStep, TimesStep, DatesStep, BuildStep {
    private String id;
    private String name;
    private List<String> times;
    private List<String> dates;
    private Integer availableTablets;
    private Integer dosage;
    private Integer requiredTimes;
    private String expirationDate;
    private User user;
    @Override
     public Medicine build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Medicine(
          id,
          name,
          availableTablets,
          dosage,
          requiredTimes,
          expirationDate,
          user,
          times,
          dates);
    }
    
    @Override
     public TimesStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public DatesStep times(List<String> times) {
        Objects.requireNonNull(times);
        this.times = times;
        return this;
    }
    
    @Override
     public BuildStep dates(List<String> dates) {
        Objects.requireNonNull(dates);
        this.dates = dates;
        return this;
    }
    
    @Override
     public BuildStep availableTablets(Integer availableTablets) {
        this.availableTablets = availableTablets;
        return this;
    }
    
    @Override
     public BuildStep dosage(Integer dosage) {
        this.dosage = dosage;
        return this;
    }
    
    @Override
     public BuildStep requiredTimes(Integer requiredTimes) {
        this.requiredTimes = requiredTimes;
        return this;
    }
    
    @Override
     public BuildStep expirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }
    
    @Override
     public BuildStep user(User user) {
        this.user = user;
        return this;
    }
    
    /** 
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     */
    public BuildStep id(String id) {
        this.id = id;
        return this;
    }
  }

}
