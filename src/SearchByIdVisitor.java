public class SearchByIdVisitor implements Visitor {
  private final int id;

  public SearchByIdVisitor(int id) {
    this.id = id;
  }

  @Override
  public Node visitNode(Node p) {
    if (p.id == id) {
      return p;
    }

    return new Project("null", 0);
  }
}