class Something:
    @require(lambda x: x > 0, description=3)
    def do_something(self, x: int) -> int:
        pass


__book_url__ = "dummy"
__book_version__ = "dummy"
