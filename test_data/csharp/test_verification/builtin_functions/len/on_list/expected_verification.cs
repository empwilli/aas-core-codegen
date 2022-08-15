/*
 * This code has been automatically generated by aas-core-codegen.
 * Do NOT edit or append.
 */

using Aas = dummyNamespace;  // renamed
using CodeAnalysis = System.Diagnostics.CodeAnalysis;
using Regex = System.Text.RegularExpressions.Regex;

using System.Collections.Generic;  // can't alias
using System.Linq;  // can't alias

namespace dummyNamespace
{
    /// <summary>
    /// Verify that the instances of the meta-model satisfy the invariants.
    /// </summary>
    /// <example>
    /// Here is an example how to verify an instance of Item:
    /// <code>
    /// var anInstance = new Aas.Item(
    ///     // ... some constructor arguments ...
    /// );
    /// foreach (var error in Verification.Verify(anInstance))
    /// {
    ///     System.Console.Writeln(
    ///         $"{error.Cause} at: " +
    ///         Reporting.GenerateJsonPath(error.PathSegments));
    /// }
    /// </code>
    /// </example>
    public static class Verification
    {
        /// <summary>
        /// Hash allowed enum values for efficient validation of enums.
        /// </summary>
        internal static class EnumValueSet
        {

        }  // internal static class EnumValueSet

        [CodeAnalysis.SuppressMessage("ReSharper", "InconsistentNaming")]
        private static readonly Verification.Transformer _transformer = (
            new Verification.Transformer());

        private class Transformer
            : Visitation.AbstractTransformer<IEnumerable<Reporting.Error>>
        {
            [CodeAnalysis.SuppressMessage("ReSharper", "NegativeEqualityExpression")]
            public override IEnumerable<Reporting.Error> Transform(
                Aas.Item that)
            {
                // No verification has been defined for Item.
                yield break;
            }

            [CodeAnalysis.SuppressMessage("ReSharper", "NegativeEqualityExpression")]
            public override IEnumerable<Reporting.Error> Transform(
                Aas.Something that)
            {
                if (!(that.SomeProperty.Count > 0))
                {
                    yield return new Reporting.Error(
                        "Invariant violated:\n" +
                        "that.SomeProperty.Count > 0");
                }

                int indexSomeProperty = 0;
                foreach (var item in that.SomeProperty)
                {
                    foreach (var error in Verification.Verify(item))
                    {
                        error.PrependSegment(
                            new Reporting.IndexSegment(
                                indexSomeProperty));
                        error.PrependSegment(
                            new Reporting.NameSegment(
                                "someProperty"));
                        yield return error;
                    }
                    indexSomeProperty++;
                }
            }
        }  // private class Transformer

        /// <summary>
        /// Verify the constraints of <paramref name="that" /> recursively.
        /// </summary>
        /// <param name="that">
        /// The instance of the meta-model to be verified
        /// </param>
        public static IEnumerable<Reporting.Error> Verify(Aas.IClass that)
        {
            foreach (var error in _transformer.Transform(that))
            {
                yield return error;
            }
        }
    }  // public static class Verification
}  // namespace dummyNamespace

/*
 * This code has been automatically generated by aas-core-codegen.
 * Do NOT edit or append.
 */
