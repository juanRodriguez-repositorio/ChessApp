/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kamus
 */
import java.util.ArrayList;
import java.util.List;
import ModelView.ChessResource;

public class ChessResourceModel {
    private List<ChessResource> resources;

    public ChessResourceModel() {
        resources = new ArrayList<>();
        loadResources();
    }

    private void loadResources() {
        resources.add(new ChessResource("Los mejores LIBROS de APERTURAS de AJEDREZ", "Video", "Apertura", "https://www.youtube.com/watch?v=-rQD-Mx6u1o"));
        resources.add(new ChessResource("El ajedrez de torneo", "Libro", "Apertura", "https://thezugzwangblog.com/los-5-mejores-libros-de-ajedrez-para-jugadores-de-club/"));
        resources.add(new ChessResource("El arte del medio juego", "Libro", "Medio Juego", "https://www.reddit.com/r/chess/comments/1ec1e20/what_booksvideos_should_i_readwatch_to_improve_my/"));
        resources.add(new ChessResource("Los 10 MEJORES libros de FINALES de AJEDREZ", "Video", "Final", "https://www.youtube.com/watch?v=V_li2OrVUSU"));
        resources.add(new ChessResource("5 MEJORES APERTURAS para aficionados (Intermedio)", "Video", "Apertura", "https://www.youtube.com/watch?v=SvyNX-XzK1I"));
        resources.add(new ChessResource("5 TRAMPAS de apertura 'buenísimas' en ajedrez BALA", "Video", "Apertura", "https://www.youtube.com/watch?v=T6a96_ZaTzY"));
        resources.add(new ChessResource("Las mejores APERTURAS de AJEDREZ", "Video", "Apertura", "https://www.youtube.com/playlist?list=PLMWPPmJLOBgaq8uajHHqa3Syh_BPgZvjh"));
        resources.add(new ChessResource("5 MEJORES aperturas para el 2024 (BLANCAS)", "Video", "Apertura", "https://www.youtube.com/watch?v=rDbILvBYrPc"));
        resources.add(new ChessResource("Las 5 aperturas de ajedrez más agresivas y sorprendentes", "Artículo", "Apertura", "https://www.chess.com/es/article/view/las-5-aperturas-de-ajedrez-mas-agresivas-y-sorprendentes"));
        resources.add(new ChessResource("Aperturas de ajedrez", "Video", "Apertura", "https://www.youtube.com/playlist?list=PLH6dDsjrf5Z2p1ArnvBRIRScgzz2suE4o"));
        resources.add(new ChessResource("La mejor apertura blanca Sub-1600 Elo", "Video", "Apertura", "https://www.youtube.com/watch?v=cBohNo6FTJE"));
        resources.add(new ChessResource("5 MEJORES aperturas para el 2024 (NEGRAS)", "Video", "Apertura", "https://www.youtube.com/watch?v=S5iRkNgclPY"));
        resources.add(new ChessResource("5 trucos de apertura para ganar al ajedrez dejando boquiabiertos a tus rivales", "Artículo", "Apertura", "https://capakhine.es/index.php/blog/214-5-trucos-de-apertura-para-ganar-al-ajedrez-dejando-boquiabiertos-a-tus-rivales"));
        resources.add(new ChessResource("La MEJOR apertura contra 1.e4 - ¡Una trampa en cada paso!", "Video", "Apertura", "https://www.youtube.com/watch?v=-S-aiSy8sco"));
        resources.add(new ChessResource("Domina las aperturas de ajedrez y sé un maestro estratégico", "Artículo", "Apertura", "https://ajedrez.wiki/domina-las-aperturas-de-ajedrez-y-se-un-maestro-estrategico-descubre-los-mejores-recursos/"));
        resources.add(new ChessResource("REPERTORIO COMPLETO de APERTURAS de AJEDREZ para principiantes", "Video", "Apertura", "https://www.youtube.com/watch?v=sFV5n85c0mo"));
        resources.add(new ChessResource("La MEJOR TRAMPA de apertura contra 1.e4", "Video", "Apertura", "https://www.youtube.com/watch?v=Yk4ens6PKks"));
        resources.add(new ChessResource("Apertura UNIVERSAL 1.b3 para las blancas (¡más PODEROSA de todas!)", "Video", "Apertura", "https://www.youtube.com/watch?v=SI0ZYiHR1Tw"));
        resources.add(new ChessResource("Las mejores aperturas de ajedrez y cómo escoger la tuya [Infografía]", "Artículo", "Apertura", "https://www.chess-teacher.es/las-mejores-aperturas-ajedrez-escoger-la-tuya-infografia/"));
        resources.add(new ChessResource("Aperturas de ajedrez | Chesscul", "Artículo", "Apertura", "https://chesscul.com/aperturas-ajedrez/"));
        resources.add(new ChessResource("5 MEJORES aperturas para el 2024 (BLANCAS)", "Video", "Apertura", "https://www.youtube.com/watch?v=rDbILvBYrPc"));
        resources.add(new ChessResource("Las 5 aperturas de ajedrez más agresivas y sorprendentes", "Artículo", "Apertura", "https://www.chess.com/es/article/view/las-5-aperturas-de-ajedrez-mas-agresivas-y-sorprendentes"));
        resources.add(new ChessResource("Aperturas de ajedrez", "Video", "Apertura", "https://www.youtube.com/playlist?list=PLH6dDsjrf5Z2p1ArnvBRIRScgzz2suE4o"));
        resources.add(new ChessResource("La mejor apertura blanca Sub-1600 Elo", "Video", "Apertura", "https://www.youtube.com/watch?v=cBohNo6FTJE"));
        resources.add(new ChessResource("Estrategia en ajedrez: Cómo planificar en el medio juego", "Artículo", "Medio Juego", "https://chesscul.com/estrategia-ajedrez/"));
        resources.add(new ChessResource("5 tácticas fundamentales en el medio juego #2", "Artículo", "Medio Juego", "https://www.chess.com/es/blog/Adriwifi/5-tacticas-fundamentales-en-el-medio-juego-2"));
        resources.add(new ChessResource("Medio juego (ajedrez)", "Artículo", "Medio Juego", "https://es.wikipedia.org/wiki/Medio_juego_%28ajedrez%29"));
        resources.add(new ChessResource("Elaboración de planes en el medio juego", "Video", "Medio Juego", "https://www.youtube.com/watch?v=f1rIqk1efjs"));
        resources.add(new ChessResource("Estrategias de Medio Juego que Todo Jugador de Ajedrez Debe Conocer", "Artículo", "Medio Juego", "https://clubajedrezlima.com/2024/05/25/estrategias-de-medio-juego-que-todo-jugador-de-ajedrez-debe-conocer-para-principiantes/"));
        resources.add(new ChessResource("Una regla vital para el medio juego", "Artículo", "Medio Juego", "https://www.chess-teacher.es/una-regla-vital-medio-juego/"));
        resources.add(new ChessResource("El Ajedrez en el Medio Juego", "Artículo", "Medio Juego", "https://www.astridvasquezcei.com/el-ajedrez-en-el-medio-juego/"));
        resources.add(new ChessResource("Cómo DOMINAR el MEDIO JUEGO en AJEDREZ", "Video", "Medio Juego", "https://www.youtube.com/watch?v=Mr9-RMn7mxk"));
        resources.add(new ChessResource("Las leyes irrefutables para vencer en el medio juego", "Artículo", "Medio Juego", "https://thezugzwangblog.com/las-leyes-irrefutables-para-vencer-en-el-medio-juego/"));
        resources.add(new ChessResource("Reglas y Normas del Medio Juego en el Ajedrez", "Artículo", "Medio Juego", "https://elrefugiodeljugador.com/reglas-y-normas-del-medio-juego-en-el-ajedrez/"));
        resources.add(new ChessResource("Estrategia y táctica en el medio juego", "Artículo", "Medio Juego", "https://www.ajedrez32.com/estrategia-y-tactica-en-el-medio-juego/"));
        resources.add(new ChessResource("Cómo mejorar en el medio juego del ajedrez", "Artículo", "Medio Juego", "https://www.ajedrez21.com/como-mejorar-en-el-medio-juego-del-ajedrez/"));
        resources.add(new ChessResource("Conceptos clave del medio juego en ajedrez", "Artículo", "Medio Juego", "https://www.tabladeflandes.com/ajedrez-para-todos/conceptos-clave-del-medio-juego-en-ajedrez/"));
        resources.add(new ChessResource("Estrategias avanzadas para el medio juego", "Artículo", "Medio Juego", "https://www.nibaldocalvo.com/estrategias-avanzadas-para-el-medio-juego/"));
        resources.add(new ChessResource("Técnicas efectivas en el medio juego", "Artículo", "Medio Juego", "https://www.ajedrezdeentrenamiento.com/tecnicas-efectivas-en-el-medio-juego/"));
        resources.add(new ChessResource("Cómo planificar en el medio juego", "Artículo", "Medio Juego", "https://www.ajedrez365.com/como-planificar-en-el-medio-juego/"));
        resources.add(new ChessResource("Errores comunes en el medio juego y cómo evitarlos", "Artículo", "Medio Juego", "https://www.ajedrezparaprincipiantes.com/errores-comunes-en-el-medio-juego-y-como-evitarlos/"));
        resources.add(new ChessResource("La importancia del medio juego en el ajedrez", "Artículo", "Medio Juego", "https://www.ajedrezestrategico.com/la-importancia-del-medio-juego-en-el-ajedrez/"));
        resources.add(new ChessResource("Cómo desarrollar un plan en el medio juego", "Artículo", "Medio Juego", "https://www.ajedrezavanzado.com/como-desarrollar-un-plan-en-el-medio-juego/"));
        resources.add(new ChessResource("Estrategias esenciales para el medio juego en ajedrez", "Artículo", "Medio Juego", "https://www.ajedreztotal.com/estrategias-esenciales-para-el-medio-juego-en-ajedrez/"));
        resources.add(new ChessResource("5 recursos mágicos en los finales de ajedrez", "Artículo", "Final", "https://www.chess.com/es/article/view/5-recursos-magicos-en-los-finales-de-ajedrez"));
        resources.add(new ChessResource("5 recursos ESENCIALES en los FINALES de ajedrez", "Video", "Final", "https://www.youtube.com/watch?v=gRbFsSC6FXU"));
        resources.add(new ChessResource("Finales de ajedrez: conceptos clave para dominar la fase final", "Artículo", "Final", "https://www.superprof.cr/blog/finales-ajedrez/"));
        resources.add(new ChessResource("3 FINALES MILAGROSOS ¡Aprende estos recursos!", "Video", "Final", "https://www.youtube.com/watch?v=Egs_jP-_koU"));
        resources.add(new ChessResource("7 elementos fundamentales en los finales de ajedrez", "Artículo", "Final", "https://capakhine.es/index.php/blog/186-7-elementos-fundamentales-en-los-finales-de-ajedrez"));
        resources.add(new ChessResource("Juegos con Finales de Ajedrez | Practica y Mejora tu Técnica", "Artículo", "Final", "https://www.ajedrezeureka.com/category/juegos-con-finales-de-ajedrez/"));
        resources.add(new ChessResource("Mejora Tus Finales de Ajedrez Con Este Simple Secreto!", "Video", "Final", "https://www.youtube.com/watch?v=2CPyL0OW9dg"));
        resources.add(new ChessResource("Finales de ajedrez: conceptos clave para dominar la fase final", "Artículo", "Final", "https://www.superprof.cr/blog/finales-ajedrez/"));
        resources.add(new ChessResource("5 recursos mágicos en los finales de ajedrez", "Artículo", "Final", "https://www.chess.com/es/article/view/5-recursos-magicos-en-los-finales-de-ajedrez"));
        resources.add(new ChessResource("5 recursos ESENCIALES en los FINALES de ajedrez", "Video", "Final", "https://www.youtube.com/watch?v=gRbFsSC6FXU"));
        resources.add(new ChessResource("3 FINALES MILAGROSOS ¡Aprende estos recursos!", "Video", "Final", "https://www.youtube.com/watch?v=Egs_jP-_koU"));
        resources.add(new ChessResource("7 elementos fundamentales en los finales de ajedrez", "Artículo", "Final", "https://capakhine.es/index.php/blog/186-7-elementos-fundamentales-en-los-finales-de-ajedrez"));
        resources.add(new ChessResource("Juegos con Finales de Ajedrez | Practica y Mejora tu Técnica", "Artículo", "Final", "https://www.ajedrezeureka.com/category/juegos-con-finales-de-ajedrez/"));
        resources.add(new ChessResource("Mejora Tus Finales de Ajedrez Con Este Simple Secreto!", "Video", "Final", "https://www.youtube.com/watch?v=2CPyL0OW9dg"));
        resources.add(new ChessResource("Finales de ajedrez: conceptos clave para dominar la fase final", "Artículo", "Final", "https://www.superprof.cr/blog/finales-ajedrez/"));
        resources.add(new ChessResource("5 recursos mágicos en los finales de ajedrez", "Artículo", "Final", "https://www.chess.com/es/article/view/5-recursos-magicos-en-los-finales-de-ajedrez"));
        resources.add(new ChessResource("5 recursos ESENCIALES en los FINALES de ajedrez", "Video", "Final", "https://www.youtube.com/watch?v=gRbFsSC6FXU"));
        resources.add(new ChessResource("3 FINALES MILAGROSOS ¡Aprende estos recursos!", "Video", "Final", "https://www.youtube.com/watch?v=Egs_jP-_koU"));
        resources.add(new ChessResource("7 elementos fundamentales en los finales de ajedrez", "Artículo", "Final", "https://capakhine.es/index.php/blog/186-7-elementos-fundamentales-en-los-finales-de-ajedrez"));
        resources.add(new ChessResource("Juegos con Finales de Ajedrez | Practica y Mejora tu Técnica", "Artículo", "Final", "https://www.ajedrezeureka.com/category/juegos-con-finales-de-ajedrez/"));
        // Agrega más recursos aquí
    }

    public List<ChessResource> getResourcesByPhase(String phase) {
        List<ChessResource> filtered = new ArrayList<>();
        for (ChessResource resource : resources) {
            if (resource.getPhase().equalsIgnoreCase(phase)) {
                filtered.add(resource);
            }
        }
        return filtered;
    }

    public List<ChessResource> getAllResources() {
        return resources;
    }
}
