/*
 * This code has been automatically generated by aas-core-codegen.
 * Do NOT edit or append.
 */

package aas_core.aas3_0.types.model;

import aas_core.aas3_0.types.enums.*;
import aas_core.aas3_0.types.impl.*;
import aas_core.aas3_0.types.model.*;
import java.util.List;
import aas_core.aas3_0.types.model.IClass;
import java.util.Optional;

/**
 * Value represented by up to four variants of a numeric value in a specific role:
 * {@literal MIN}, {@literal NOM}, {@literal TYP} and {@literal MAX}. True means that the value is available,
 * false means the value is not available.
 *
 * <p>EXAMPLE from [IEC61360-1]: In the case of having a property which is
 * of the LEVEL_TYPE min/max − expressing a range − only those two values
 * need to be provided.
 *
 * <p>This is how AAS deals with the following combinations of level types:
 * <ul>
 *   <li> Either all attributes are false. In this case the concept is mapped
 *   to a {@link Property} and level type is ignored.
 *   <li> At most one of the attributes is set to true. In this case
 *   the concept is mapped to a {@link Property}.
 *   <li> Min and max are set to true. In this case the concept is mapped
 *   to a {@link Range}.
 *   <li> More than one attribute is set to true but not min and max only
 *   (see second case). In this case the concept is mapped
 *   to a {@link SubmodelElementCollection} with the corresponding
 *   number of Properties.
 *   Example: If attribute {@link LevelType#getMin min} and {@link LevelType#getNom nom} are set to true
 *   then the concept is mapped to a {@link SubmodelElementCollection}
 *   with two Properties within: min and nom.
 *   The data type of both Properties is the same.
 * </ul>
 *
 * <p>In the cases 2. and 4. the {@link Property#getSemanticId semanticId} of the Property
 * or Properties within the {@link SubmodelElementCollection} needs to include
 * information about the level type. Otherwise, the semantics is not described
 * in a unique way. Please refer to the specification.
 */
public interface ILevelType extends IClass {
  /**
   * Minimum of the value
   */
  Boolean getMin();

  void setMin(Boolean min);

  /**
   * Nominal value (value as designated)
   */
  Boolean getNom();

  void setNom(Boolean nom);

  /**
   * Value as typically present
   */
  Boolean getTyp();

  void setTyp(Boolean typ);

  /**
   * Maximum of the value
   */
  Boolean getMax();

  void setMax(Boolean max);
}

/*
 * This code has been automatically generated by aas-core-codegen.
 * Do NOT edit or append.
 */
