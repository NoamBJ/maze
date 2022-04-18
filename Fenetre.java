import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Fenetre extends JFrame {

    final int taille = 40000;
    private JButton[] emplacement = new JButton[taille];
    private HashMap<JButton, Integer> map = new HashMap<>();
    Random rand = new Random();

    public Fenetre() {
        super("Maze");

        for (int i = 0; i < emplacement.length; i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            emplacement[i] = new JButton();
            emplacement[i].setBackground(randomColor);
            emplacement[i].setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1,
                    Color.BLACK));
            emplacement[i].setOpaque(true);
            emplacement[i].setFont(new Font("Arial", Font.BOLD, 42));
            emplacement[i].setFocusable(false);
            // emplacement[i].setText("" + i);
            map.put(emplacement[i], i);
            add(emplacement[i]);
        }
        Graph vertices = createMaze_DFS(emplacement, map);
        breadthFirstSearch(0, taille - 1, vertices, emplacement);
        setLayout(new GridLayout((int) (Math.sqrt(taille)), (int) (Math.sqrt(taille))));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    public Graph createMaze_DFS(JButton[] buttons, HashMap<JButton, Integer> map) {
        ArrayList<Quadruple> use_borders = new ArrayList<>();
        Graph graph = new Graph();
        for (int i = 0; i < taille; i++) {
            use_borders.add(new Quadruple(1, 1, 1, 1));
            graph.addVertex(i);
        }

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color colorMaze = new Color(r, g, b);
        Deque<JButton> stack = new ArrayDeque<JButton>();
        boolean[] visited = new boolean[taille];
        int ligne = (int) (Math.sqrt(taille));
        int start = (int) (Math.random() * taille);
        stack.push(buttons[start]);
        visited[start] = true;
        while (!stack.isEmpty()) {
            JButton current_cell = stack.pop();
            int i = map.get(current_cell);
            // System.out.println(i);

            // check neighbours pour les 4 coins
            if (i == 0 && (visited[i + 1] == false || visited[i + ligne] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i + 1] == false) {
                    temp.add(1);
                }
                if (visited[i + ligne] == false) {
                    temp.add(ligne);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == 1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + 1].setBackground(colorMaze.brighter());
                    use_borders.get(i + 1).setGauche(0);
                    use_borders.get(i).setDroite(0);
                    graph.addEdge(i, i + 1);
                    visited[i + 1] = true;
                    stack.push(buttons[i + 1]);
                }
                if (random == ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i + ligne).setHaut(0);
                    use_borders.get(i).setBas(0);
                    graph.addEdge(i, i + ligne);
                    visited[i + ligne] = true;
                    stack.push(buttons[i + ligne]);
                }
            } else if (i == ligne - 1 && (visited[i - 1] == false || visited[i + ligne] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i - 1] == false) {
                    temp.add(-1);
                }
                if (visited[i + ligne] == false) {
                    temp.add(ligne);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == -1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - 1].setBackground(colorMaze.brighter());
                    use_borders.get(i).setGauche(0);
                    use_borders.get(i - 1).setDroite(0);
                    graph.addEdge(i, i - 1);
                    visited[i - 1] = true;
                    stack.push(buttons[i - 1]);
                }
                if (random == ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i + ligne).setHaut(0);
                    use_borders.get(i).setBas(0);
                    graph.addEdge(i, i + ligne);
                    visited[i + ligne] = true;
                    stack.push(buttons[i + ligne]);
                }
            } else if (i == taille - ligne && (visited[i + 1] == false || visited[i - ligne] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i + 1] == false) {
                    temp.add(1);
                }
                if (visited[i - ligne] == false) {
                    temp.add(-ligne);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == 1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + 1].setBackground(colorMaze.brighter());
                    use_borders.get(i + 1).setGauche(0);
                    use_borders.get(i).setDroite(0);
                    graph.addEdge(i, i + 1);
                    visited[i + 1] = true;
                    stack.push(buttons[i + 1]);
                }
                if (random == -ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i - ligne).setBas(0);
                    use_borders.get(i).setHaut(0);
                    graph.addEdge(i, i - ligne);
                    visited[i - ligne] = true;
                    stack.push(buttons[i - ligne]);
                }
            } else if (i == taille - 1 && (visited[i - 1] == false || visited[i - ligne] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i - 1] == false) {
                    temp.add(-1);
                }
                if (visited[i - ligne] == false) {
                    temp.add(-ligne);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == -1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - 1].setBackground(colorMaze.brighter());
                    use_borders.get(i).setGauche(0);
                    use_borders.get(i - 1).setDroite(0);
                    graph.addEdge(i, i - 1);
                    visited[i - 1] = true;
                    stack.push(buttons[i - 1]);
                }
                if (random == -ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i - ligne).setBas(0);
                    use_borders.get(i).setHaut(0);
                    graph.addEdge(i, i - ligne);
                    visited[i - ligne] = true;
                    stack.push(buttons[i - ligne]);
                }
            }

            // check neighbours pour les 4 lignes aux bords
            else if (i < ligne && i != 0 && i != ligne - 1
                    && (visited[i + 1] == false || visited[i + ligne] == false || visited[i - 1] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i + 1] == false) {
                    temp.add(1);
                }
                if (visited[i + ligne] == false) {
                    temp.add(ligne);
                }
                if (visited[i - 1] == false) {
                    temp.add(-1);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == 1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + 1].setBackground(colorMaze.brighter());
                    use_borders.get(i + 1).setGauche(0);
                    use_borders.get(i).setDroite(0);
                    graph.addEdge(i, i + 1);
                    visited[i + 1] = true;
                    stack.push(buttons[i + 1]);
                }
                if (random == -1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - 1].setBackground(colorMaze.brighter());
                    use_borders.get(i).setGauche(0);
                    use_borders.get(i - 1).setDroite(0);
                    graph.addEdge(i, i - 1);
                    visited[i - 1] = true;
                    stack.push(buttons[i - 1]);
                }
                if (random == ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i + ligne).setHaut(0);
                    use_borders.get(i).setBas(0);
                    graph.addEdge(i, i + ligne);
                    visited[i + ligne] = true;
                    stack.push(buttons[i + ligne]);
                }
            } else if (i + ligne > taille && i != taille - 1 && i != taille - ligne
                    && (visited[i - 1] == false || visited[i - ligne] == false || visited[i + 1] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i + 1] == false) {
                    temp.add(1);
                }
                if (visited[i - ligne] == false) {
                    temp.add(-ligne);
                }
                if (visited[i - 1] == false) {
                    temp.add(-1);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == 1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + 1].setBackground(colorMaze.brighter());
                    use_borders.get(i + 1).setGauche(0);
                    use_borders.get(i).setDroite(0);
                    graph.addEdge(i, i + 1);
                    visited[i + 1] = true;
                    stack.push(buttons[i + 1]);
                }
                if (random == -1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - 1].setBackground(colorMaze.brighter());
                    use_borders.get(i).setGauche(0);
                    use_borders.get(i - 1).setDroite(0);
                    graph.addEdge(i, i - 1);
                    visited[i - 1] = true;
                    stack.push(buttons[i - 1]);
                }
                if (random == -ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i - ligne).setBas(0);
                    use_borders.get(i).setHaut(0);
                    graph.addEdge(i, i - ligne);
                    visited[i - ligne] = true;
                    stack.push(buttons[i - ligne]);
                }
            } else if (i % ligne == 0 && i != 0 && i != taille - ligne
                    && (visited[i + 1] == false || visited[i + ligne] == false || visited[i - ligne] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i - ligne] == false) {
                    temp.add(-ligne);
                }
                if (visited[i + ligne] == false) {
                    temp.add(ligne);
                }
                if (visited[i + 1] == false) {
                    temp.add(1);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == 1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + 1].setBackground(colorMaze.brighter());
                    use_borders.get(i + 1).setGauche(0);
                    use_borders.get(i).setDroite(0);
                    graph.addEdge(i, i + 1);
                    visited[i + 1] = true;
                    stack.push(buttons[i + 1]);
                }
                if (random == ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i + ligne).setHaut(0);
                    use_borders.get(i).setBas(0);
                    graph.addEdge(i, i + ligne);
                    visited[i + ligne] = true;
                    stack.push(buttons[i + ligne]);
                }
                if (random == -ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i - ligne).setBas(0);
                    use_borders.get(i).setHaut(0);
                    graph.addEdge(i, i - ligne);
                    visited[i - ligne] = true;
                    stack.push(buttons[i - ligne]);
                }
            } else if ((i + 1) % ligne == 0 && i != ligne - 1 && i != taille - 1
                    && (visited[i - 1] == false || visited[i + ligne] == false || visited[i - ligne] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i - ligne] == false) {
                    temp.add(-ligne);
                }
                if (visited[i + ligne] == false) {
                    temp.add(ligne);
                }
                if (visited[i - 1] == false) {
                    temp.add(-1);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == -1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - 1].setBackground(colorMaze.brighter());
                    use_borders.get(i).setGauche(0);
                    use_borders.get(i - 1).setDroite(0);
                    graph.addEdge(i, i - 1);
                    visited[i - 1] = true;
                    stack.push(buttons[i - 1]);
                }
                if (random == ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i + ligne).setHaut(0);
                    use_borders.get(i).setBas(0);
                    graph.addEdge(i, i + ligne);
                    visited[i + ligne] = true;
                    stack.push(buttons[i + ligne]);
                }
                if (random == -ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i - ligne).setBas(0);
                    use_borders.get(i).setHaut(0);
                    graph.addEdge(i, i - ligne);
                    visited[i - ligne] = true;
                    stack.push(buttons[i - ligne]);
                }

                // ne rentre jamais dans cette boucle

            } else if (i != 0 && i != ligne - 1 && i != taille - 1 && i != taille - ligne && i > ligne &&
                    i + ligne < taille && i % ligne != 0 && (i + 1) % ligne != 0
                    && (visited[i - 1] == false || visited[i + 1] == false || visited[i + ligne] == false
                            || visited[i - ligne] == false)) {
                stack.push(current_cell);
                ArrayList<Integer> temp = new ArrayList<>();
                if (visited[i - ligne] == false) {
                    temp.add(-ligne);
                }
                if (visited[i + ligne] == false) {
                    temp.add(ligne);
                }
                if (visited[i - 1] == false) {
                    temp.add(-1);
                }
                if (visited[i + 1] == false) {
                    temp.add(1);
                }
                int random = temp.get((int) (Math.random() * temp.size()));
                if (random == -1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - 1].setBackground(colorMaze.brighter());
                    use_borders.get(i).setGauche(0);
                    use_borders.get(i - 1).setDroite(0);
                    graph.addEdge(i, i - 1);
                    visited[i - 1] = true;
                    stack.push(buttons[i - 1]);
                }
                if (random == ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i + ligne).setHaut(0);
                    use_borders.get(i).setBas(0);
                    graph.addEdge(i, i + ligne);
                    visited[i + ligne] = true;
                    stack.push(buttons[i + ligne]);
                }
                if (random == -ligne) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i - ligne].setBackground(colorMaze.brighter());
                    use_borders.get(i - ligne).setBas(0);
                    use_borders.get(i).setHaut(0);
                    graph.addEdge(i, i - ligne);
                    visited[i - ligne] = true;
                    stack.push(buttons[i - ligne]);
                }
                if (random == 1) {
                    current_cell.setBackground(colorMaze.brighter());
                    buttons[i + 1].setBackground(colorMaze.brighter());
                    use_borders.get(i + 1).setGauche(0);
                    use_borders.get(i).setDroite(0);
                    graph.addEdge(i, i + 1);
                    visited[i + 1] = true;
                    stack.push(buttons[i + 1]);
                }
            }
        }
        for (int i = 0; i < taille; i++) {
            int haut = use_borders.get(i).getHaut();
            int gauche = use_borders.get(i).getGauche();
            int bas = use_borders.get(i).getBas();
            int droite = use_borders.get(i).getDroite();
            buttons[i].setBorder(BorderFactory.createMatteBorder(haut, gauche, bas, droite,
                    Color.BLACK));
        }
        return graph;
    }

    public LinkedList<Vertex> breadthFirstSearch(int start, int end, Graph vertices, JButton[] buttons) {

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();
        Color solution_color = new Color(r, g, b);

        Vertex startVert = vertices.getVertices().get(start);
        Vertex endVert = vertices.getVertices().get(end);

        LinkedList<Vertex> queue = new LinkedList<>(); // LinkedList implements Queue
        HashMap<Vertex, Vertex> visited = new HashMap<>();

        visited.put(startVert, null); // this is the first vertex

        Vertex current = startVert; // the current vertex to check
        while (current != endVert) { // repeats until the end is reached

            LinkedList<Vertex> adjacents = current.getAdjacents(); // get adjacents

            for (Vertex v : adjacents) { // add all the adjacents
                if (!visited.containsKey(v)) { // but only if it hasn't already been traversed
                    visited.put(v, current);
                    queue.add(v);
                }
            }

            current = queue.remove(); // goes to the next vertex
        }

        // create the path
        LinkedList<Vertex> path = new LinkedList<>();
        path.addFirst(current);
        int result = 1;
        while (current != startVert) {
            // System.out.println(vertices.get(current));
            result++;
            current = visited.get(current);
            path.addFirst(current); // addFirst is used to get the correct order
            buttons[vertices.get(current)].setBackground(solution_color);
        }
        buttons[vertices.get(endVert)].setBackground(solution_color);
        // JOptionPane.showMessageDialog(this, "Je suis trop chaud le chemin le plus
        // court utilise " + result + " boutons",
        // "salut", 1);
        return path;
    }
}