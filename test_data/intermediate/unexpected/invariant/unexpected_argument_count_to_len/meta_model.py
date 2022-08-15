class Item:
    pass


# fmt: off
@invariant(
    lambda self:
    len(self.some_property, 3) > 0
)
# fmt: on
class Something:
    some_property: List[Item]

    def __init__(self, some_property: Item) -> None:
        self.some_property = some_property


__book_url__ = "dummy"
__book_version__ = "dummy"
