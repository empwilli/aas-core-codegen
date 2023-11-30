"""Generate Java code for reporting errors by including the code directly."""

import io
import textwrap
from typing import List

from icontract import ensure

from aas_core_codegen.common import (
    Stripped,
)
from aas_core_codegen.java import (
    common as java_common,
)
from aas_core_codegen.csharp.common import (
    INDENT as I,
    INDENT2 as II,
    INDENT3 as III,
    INDENT4 as IIII,
    INDENT5 as IIIII,
)


# fmt: off
@ensure(
    lambda result:
    result.endswith('\n'),
    "Trailing newline mandatory for valid end-of-files"
)
# fmt: on
def generate(package: java_common.PackageIdentifier) -> str:
    """
    Generate the Java code for reporting errors.

    The ``package`` defines the AAS Java package.
    """

    blocks = [
        Stripped(
            f"""\
/**
 * Capture a path segment of a value in a model.
 */
public interface Segment {{
{I}// Intentionally empty.
}}"""
        ),
        Stripped(
            f"""\
public class NameSegment implements Segment {{
{I}private String name;

{I}public NameSegment(String name) {{
{II}this.name = name;
{I}}}

{I}public String getName() {{
{II}return name;
{I}}}
}}"""
        ),
        Stripped(
            f"""\
public class IndexSegment implements Segment {{
{I}private int index;

{I}public IndexSegment(int index) {{
{II}this.index = index;
{I}}}

{I}public int getIndex() {{
{II}return index;
{I}}}
}}"""
        ),
        Stripped(
            f"""\
private static final Pattern variableNameRe = Pattern.compile("^[a-zA-Z_][a-zA-Z_0-9]*$");"""
        ),
        # We have to indent a lot, so we do not use textwrap.dedent for better
        # readability.
        Stripped(
            f"""\
/**
 * Generate a JSON Path based on the path segments.
 *
 * <p>See, for example, this page for more information on JSON path:
 * https://support.smartbear.com/alertsite/docs/monitors/api/endpoint/jsonpath.html
 */
public static String generateJsonPath(List<Segment> segments) {{
{I}ArrayList<String> parts = new ArrayList<String>(segments.size());
{I}int i = 0;

{I}for (Segment segment : segments) {{
{II}String part;

{II}if (segment instanceof NameSegment) {{
{III}NameSegment nameSegment = (NameSegment) segment;
{III}Matcher m = variableNameRe.matcher(nameSegment.getName());

{III}if (m.matches()) {{
{IIII}part = (i == 0) ? nameSegment.getName() : "." + nameSegment.getName() + "";
{III}}} else {{
{IIII}String escaped = nameSegment.getName()
{IIIII}.replace("\\t", "\\\\t")
{IIIII}.replace("\\b", "\\\\b")
{IIIII}.replace("\\n", "\\\\n")
{IIIII}.replace("\\r", "\\\\r")
{IIIII}.replace("\\f", "\\\\f")
{IIIII}.replace("\\"", "\\\\\\"")
{IIIII}.replace("\\\\", "\\\\\\\\");

{IIII}part = "[\\"" + escaped + "\\"]";
{III}}}
{II}}} else if (segment instanceof IndexSegment) {{
{III}IndexSegment indexSegment = (IndexSegment) segment;
{III}part = "[" + indexSegment.getIndex() + "]";
{II}}} else {{
{III}throw new RuntimeException(
{IIII}"Unexpected segment type: " + segment.getClass().getSimpleName()
{III});
{II}}}

{II}parts.add(part);
{II}i++;
{I}}}
{I}return String.join("", parts);
}}"""
        ),
        Stripped(
            f"""\
/**
 * Escape special characters for XPath.
 */
private static String escapeForXPath(String text) {{
{I}return text
{III}// Even though ampersand, less-then etc. cannot occur in valid element names,
{III}// we escape them here for easier debugging and better bug reports.
{III}.replace("&", "&amp;")
{III}.replace("/", "&#47;")
{III}.replace("<", "&lt;")
{III}.replace(">", "&gt;")
{III}.replace("\\"", "&quot;")
{III}.replace("'", "&apos;");
}}"""
        ),
        Stripped(
            f"""\
/**
 * Generate a relative XPath based on the path segments.
 *
 * <p>This method leaves out the leading slash ('/'). This is helpful if
 * to embed the error report in a larger document with a prefix etc.
 */
public static String generateRelativeXPath(List<Segment> segments) {{
{I}ArrayList<String> parts = new ArrayList<String>(segments.size());

{I}for (Segment segment : segments) {{
{II}String part;

{II}if (segment instanceof NameSegment) {{
{III}NameSegment nameSegment = (NameSegment) segment;
{III}part = escapeForXPath(nameSegment.getName());
{II}}} else if (segment instanceof IndexSegment) {{
{III}IndexSegment indexSegment = (IndexSegment) segment;
{III}part = "*[" + indexSegment.getIndex() + "]";
{II}}} else {{
{III}throw new RuntimeException(
{IIII}"Unexpected segment type: " + segment.getClass().getSimpleName()
{III});
{II}}}

{II}parts.add(part);
{I}}}
{I}return String.join("/", parts);
}}"""
        ),
        Stripped(
            f"""\
/**
 * Represent an error during the deserialization or the verification.
 */
public class Error
{{
{I}private final LinkedList<Segment> pathSegments = new LinkedList<Segment>();
{I}private final String cause;

{I}public Error(String cause)
{I}{{
{II}this.cause = cause;
{I}}}

{I}public void PrependSegment(Segment segment)
{I}{{
{II}pathSegments.addFirst(segment);
{I}}}

{I}public List<Segment> getPathSegments() {{
{II}return Collections.unmodifiableList(pathSegments);
{I}}}
}}"""
        ),
    ]  # type: List[Stripped]

    writer = io.StringIO()
    writer.write(
        f"""\
package {package}.reporting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provide reporting for de/serialization and verification.
 */
public class Reporting
{{
"""
    )

    for i, deserialize_block in enumerate(blocks):
        if i > 0:
            writer.write("\n\n")

        writer.write(textwrap.indent(deserialize_block, I))

    writer.write(
        f"""
}}

// package {package}.reporting"""
    )

    blocks = [
        java_common.WARNING,
        Stripped(writer.getvalue()),
        java_common.WARNING,
    ]

    writer = io.StringIO()
    for i, block in enumerate(blocks):
        if i > 0:
            writer.write("\n\n")

        assert not block.startswith("\n")
        assert not block.endswith("\n")
        writer.write(block)

    writer.write("\n")

    return writer.getvalue()
