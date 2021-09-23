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
  public static final QueryField NUMBER_OF_TABLETS = field("Medicine", "numberOfTablets");
  public static final QueryField DOSAGE = field("Medicine", "dosage");
  public static final QueryField USER = field("Medicine", "userId");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String name;
  private final @ModelField(targetType="Int") Integer numberOfTablets;
  private final @ModelField(targetType="Int", isRequired = true) Integer dosage;
  private final @ModelField(targetType="User") @BelongsTo(targetName = "userId", type = User.class) User user;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getName() {
      return name;
  }
  
  public Integer getNumberOfTablets() {
      return numberOfTablets;
  }
  
  public Integer getDosage() {
      return dosage;
  }
  
  public User getUser() {
      return user;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private Medicine(String id, String name, Integer numberOfTablets, Integer dosage, User user) {
    this.id = id;
    this.name = name;
    this.numberOfTablets = numberOfTablets;
    this.dosage = dosage;
    this.user = user;
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
              ObjectsCompat.equals(getNumberOfTablets(), medicine.getNumberOfTablets()) &&
              ObjectsCompat.equals(getDosage(), medicine.getDosage()) &&
              ObjectsCompat.equals(getUser(), medicine.getUser()) &&
              ObjectsCompat.equals(getCreatedAt(), medicine.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), medicine.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getName())
      .append(getNumberOfTablets())
      .append(getDosage())
      .append(getUser())
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
      .append("numberOfTablets=" + String.valueOf(getNumberOfTablets()) + ", ")
      .append("dosage=" + String.valueOf(getDosage()) + ", ")
      .append("user=" + String.valueOf(getUser()) + ", ")
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
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      name,
      numberOfTablets,
      dosage,
      user);
  }
  public interface NameStep {
    DosageStep name(String name);
  }
  

  public interface DosageStep {
    BuildStep dosage(Integer dosage);
  }
  

  public interface BuildStep {
    Medicine build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep numberOfTablets(Integer numberOfTablets);
    BuildStep user(User user);
  }
  

  public static class Builder implements NameStep, DosageStep, BuildStep {
    private String id;
    private String name;
    private Integer dosage;
    private Integer numberOfTablets;
    private User user;
    @Override
     public Medicine build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new Medicine(
          id,
          name,
          numberOfTablets,
          dosage,
          user);
    }
    
    @Override
     public DosageStep name(String name) {
        Objects.requireNonNull(name);
        this.name = name;
        return this;
    }
    
    @Override
     public BuildStep dosage(Integer dosage) {
        Objects.requireNonNull(dosage);
        this.dosage = dosage;
        return this;
    }
    
    @Override
     public BuildStep numberOfTablets(Integer numberOfTablets) {
        this.numberOfTablets = numberOfTablets;
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
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String name, Integer numberOfTablets, Integer dosage, User user) {
      super.id(id);
      super.name(name)
        .dosage(dosage)
        .numberOfTablets(numberOfTablets)
        .user(user);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
    }
    
    @Override
     public CopyOfBuilder dosage(Integer dosage) {
      return (CopyOfBuilder) super.dosage(dosage);
    }
    
    @Override
     public CopyOfBuilder numberOfTablets(Integer numberOfTablets) {
      return (CopyOfBuilder) super.numberOfTablets(numberOfTablets);
    }
    
    @Override
     public CopyOfBuilder user(User user) {
      return (CopyOfBuilder) super.user(user);
    }
  }
  
}
